package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Auditorium;
import org.example.bookmyshow_lld.models.Seat;

import java.util.List;

public interface ISeatService {
    public Seat createSeat(Long auditoriumId, Seat seat);
    public List<Seat> getSeats(Long auditoriumId);
    public Seat updateSeat(Long id, Seat updated);
    public void deleteSeat(Long id);
}
