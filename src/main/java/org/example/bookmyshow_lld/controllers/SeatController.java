package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.models.Seat;
import org.example.bookmyshow_lld.services.ISeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {

    private final ISeatService seatService;

    public SeatController(ISeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/auditorium/{auditoriumId}")
    public Seat createSeat(@PathVariable Long auditoriumId, @RequestBody Seat seat) {
        return seatService.createSeat(auditoriumId, seat);
    }

    @GetMapping("/auditorium/{auditoriumId}")
    public List<Seat> getSeats(@PathVariable Long auditoriumId) {
        return seatService.getSeats(auditoriumId);
    }

    @GetMapping("/{id}")
    public List<Seat> getSeat(@PathVariable Long id) {
        return seatService.getSeats(id);
    }

    @PutMapping("/{id}")
    public Seat update(@PathVariable Long id, @RequestBody Seat seat) {
        return seatService.updateSeat(id, seat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }
}
