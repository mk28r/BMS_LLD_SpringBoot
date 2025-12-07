package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.*;
import org.example.bookmyshow_lld.repositories.ShowRepository;
import org.example.bookmyshow_lld.repositories.ShowSeatRepository;
import org.example.bookmyshow_lld.repositories.TicketRepository;
import org.example.bookmyshow_lld.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RedisBookingService implements IBookingService {

    private final CacheService cacheService;
    private final ShowSeatRepository showSeatRepository;
    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    private static final long LOCK_TTL_SECONDS = 120;

    public RedisBookingService(
            RedisService cacheService,
            ShowSeatRepository showSeatRepository,
            TicketRepository ticketRepository,
            ShowRepository showRepository,
            UserRepository userRepository
    ) {
        this.cacheService = cacheService;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }

    // Helper key builder
    private String seatLockKey(long showSeatId) {
        return "seat:" + showSeatId;
    }

    @Override
    public boolean blockSeats(long showId, List<Long> seatIds, long userId) {

        System.out.println("Cache before blockSeats:");
        cacheService.getAllKeysAndValues();

        // 1. Load ShowSeats for given show + seatIds
        List<ShowSeat> showSeats =
                showSeatRepository.findAllByShowsIdAndSeatIdIn(showId, seatIds);

        if (showSeats.size() != seatIds.size()) {
            // Some seatIds are invalid for this show
            System.out.println("Some seat IDs are invalid for show " + showId);
            return false;
        }

        // 2. Check if any seat is already BOOKED
        for (ShowSeat seat : showSeats) {
            if (seat.getShowSeatStatus() == ShowSeatStatus.BOOKED) {
                System.out.println("Seat " + seat.getId() + " already BOOKED");
                return false;
            }
        }

        // 3. Try to acquire locks in Redis atomically
        List<Long> acquiredLocks = new ArrayList<>();

        for (ShowSeat seat : showSeats) {
            String key = seatLockKey(seat.getId());
            Object existing = cacheService.get(key);

            if (existing != null) {
                String owner = existing.toString();
                // If same user, we can treat as "already locked by you"
                if (!owner.equals(String.valueOf(userId))) {
                    // Locked by someone else -> fail
                    System.out.println("Seat " + seat.getId() +
                            " is already locked by another user: " + owner);
                    // Rollback any previously acquired locks
                    for (Long lockedSeatId : acquiredLocks) {
                        cacheService.delete(seatLockKey(lockedSeatId));
                    }
                    return false;
                } else {
                    // Same user -> refresh TTL
                    cacheService.set(key, String.valueOf(userId));
                    acquiredLocks.add(seat.getId());
                    continue;
                }
            }

            // No existing lock -> try to lock with setIfAbsent
            boolean locked = cacheService.setIfAbsent(
                    key,
                    String.valueOf(userId),
                    LOCK_TTL_SECONDS
            );

            if (!locked) {
                System.out.println("Failed to acquire lock for seat " + seat.getId());
                // Rollback any previously acquired locks
                for (Long lockedSeatId : acquiredLocks) {
                    cacheService.delete(seatLockKey(lockedSeatId));
                }
                return false;
            }

            acquiredLocks.add(seat.getId());
        }

        System.out.println("Cache after blockSeats:");
        cacheService.getAllKeysAndValues();

        return true;
    }

    @Override
    @Transactional
    public Optional<Ticket> bookTicket(long showId, List<Long> showSeatIds, long userId) {

        // 1. Validate locks in Redis: all seats must be locked by this user
        for (Long seatId : showSeatIds) {
            String key = seatLockKey(seatId);
            Object val = cacheService.get(key);
            String owner = val != null ? val.toString() : null;

            System.out.println("Redis lock check: seatId=" + seatId +
                    ", owner=" + owner + ", expectedUser=" + userId);

            if (owner == null || !owner.equals(String.valueOf(userId))) {
                System.out.println("User " + userId +
                        " does not hold lock for seat " + seatId);
                return Optional.empty();
            }
        }

        // 2. Double-check in DB: none of these seats are already BOOKED
        List<ShowSeat> seatsFromDb =
                showSeatRepository.findAllByShowsIdAndSeatIdIn(showId, showSeatIds);

        if (seatsFromDb.size() != showSeatIds.size()) {
            System.out.println("Some seat IDs invalid for show " + showId);
            return Optional.empty();
        }

        for (ShowSeat ss : seatsFromDb) {
            if (ss.getShowSeatStatus() == ShowSeatStatus.BOOKED) {
                System.out.println("Seat " + ss.getId() + " is already BOOKED in DB");
                return Optional.empty();
            }
        }

        // 3. Load User and Show
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Shows show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found: " + showId));

        try {
            // 4. Create ticket + mark seats as BOOKED in one transaction
            Ticket ticket = createTicketAndBookSeat(show, user, showSeatIds);

            // 5. On success, remove locks for these seats
            for (Long seatId : showSeatIds) {
                cacheService.delete(seatLockKey(seatId));
            }

            System.out.println("Ticket created with id " + ticket.getId());
            return Optional.of(ticket);

        } catch (RuntimeException ex) {
            // In case of any failure, remove locks so user can retry or others can book
            System.out.println("Booking failed, cleaning up locks: " + ex.getMessage());
            for (Long seatId : showSeatIds) {
                cacheService.delete(seatLockKey(seatId));
            }
            throw ex; // transaction will rollback DB changes
        }
    }

    /**
     * Called inside a @Transactional method.
     * Creates ticket and books all seats in DB.
     */
    private Ticket createTicketAndBookSeat(Shows show, User user, List<Long> seatIds) {

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setStatus(TicketStatus.BOOKED);
         ticket.setAmount(100);

        ticket = ticketRepository.save(ticket);

        // Bulk update seats: set status = BOOKED, set ticket reference
        showSeatRepository.bookShowSeatsBulk(seatIds, ticket,ShowSeatStatus.BOOKED );


        return ticket;
    }

    @Override
    public void clearAllSeatLocks() {
        cacheService.deleteAll();
    }
}
