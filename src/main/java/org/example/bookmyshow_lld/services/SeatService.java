package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Auditorium;
import org.example.bookmyshow_lld.models.Seat;
import org.example.bookmyshow_lld.repositories.AuditoriumRepository;
import org.example.bookmyshow_lld.repositories.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements ISeatService {

    private final SeatRepository seatRepo;
    private final AuditoriumRepository auditoriumRepo;

    public SeatService(SeatRepository seatRepo, AuditoriumRepository auditoriumRepo) {
        this.seatRepo = seatRepo;
        this.auditoriumRepo = auditoriumRepo;
    }

    @Override
    public Seat createSeat(Long auditoriumId, Seat seat) {
        Auditorium a = auditoriumRepo.findById(auditoriumId)
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));
        seat.setAuditorium(a);
        return seatRepo.save(seat);
    }

    @Override
    public List<Seat> getSeats(Long auditoriumId) {
        return seatRepo.findByAuditoriumId(auditoriumId);
    }

    @Override
    public Seat updateSeat(Long id, Seat updated) {
        Seat s = seatRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        s.setSeatNumber(updated.getSeatNumber());
        s.setRowVal(updated.getRowVal());
        s.setColumnVal(updated.getColumnVal());
        s.setSeatType(updated.getSeatType());

        return seatRepo.save(s);
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepo.deleteById(id);
    }
}

