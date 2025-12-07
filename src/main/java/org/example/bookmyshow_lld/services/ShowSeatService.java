package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Seat;
import org.example.bookmyshow_lld.models.ShowSeat;
import org.example.bookmyshow_lld.models.ShowSeatStatus;
import org.example.bookmyshow_lld.models.Shows;
import org.example.bookmyshow_lld.repositories.SeatRepository;
import org.example.bookmyshow_lld.repositories.ShowRepository;
import org.example.bookmyshow_lld.repositories.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowSeatService implements IShowSeatService {

    private final ShowSeatRepository showSeatRepo;
    private final ShowRepository showRepo;
    private final SeatRepository seatRepo;

    public ShowSeatService(ShowSeatRepository showSeatRepo,
                           ShowRepository showRepo,
                           SeatRepository seatRepo) {
        this.showSeatRepo = showSeatRepo;
        this.showRepo = showRepo;
        this.seatRepo = seatRepo;
    }

    // CREATE ShowSeat (assigning seat to a show)
    @Override
    public ShowSeat createShowSeat(Long showId, Long seatId, ShowSeat showSeat) {
        Shows show = showRepo.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Seat seat = seatRepo.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        showSeat.setShows(show);
        showSeat.setSeat(seat);
        showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);

        return showSeatRepo.save(showSeat);
    }

    // READ — All show seats for a show
    @Override
    public List<ShowSeat> getShowSeatsByShow(Long showId) {
        return showSeatRepo.findByShowsId(showId);
    }

    // READ by ID
    @Override
    public Optional<ShowSeat> getShowSeatById(Long id) {
        return showSeatRepo.findById(id);
    }

    // UPDATE single show seat details
    @Override
    public ShowSeat updateShowSeat(Long id, ShowSeat updatedShowSeat) {
        ShowSeat ss = showSeatRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("ShowSeat not found"));

        ss.setShowSeatStatus(updatedShowSeat.getShowSeatStatus());
        return showSeatRepo.save(ss);
    }

    // DELETE a show seat
    @Override
    public void deleteShowSeat(Long id) {
        showSeatRepo.deleteById(id);
    }

    // UPDATE status of one seat (BLOCKED, BOOKED, AVAILABLE)
    @Override
    public ShowSeat updateShowSeatStatus(Long id, ShowSeatStatus status) {
        ShowSeat ss = showSeatRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("ShowSeat not found"));

        ss.setShowSeatStatus(status);
        return showSeatRepo.save(ss);
    }

    // BULK UPDATE status for booking/locking flows
    @Override
    public List<ShowSeat> updateSeatsStatus(List<Long> seatIds, ShowSeatStatus status) {
        List<ShowSeat> seats = showSeatRepo.findAllById(seatIds);

        seats.forEach(s -> s.setShowSeatStatus(status));

        return showSeatRepo.saveAll(seats);
    }
}
