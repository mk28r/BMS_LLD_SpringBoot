package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.models.ShowSeat;
import org.example.bookmyshow_lld.models.ShowSeatStatus;
import org.example.bookmyshow_lld.services.IShowSeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/show-seats")
public class ShowSeatController {

    private final IShowSeatService showSeatService;

    public ShowSeatController(IShowSeatService showSeatService) {
        this.showSeatService = showSeatService;
    }

    @PostMapping("/show/{showId}/seat/{seatId}")
    public ShowSeat createShowSeat(
            @PathVariable Long showId,
            @PathVariable Long seatId,
            @RequestBody ShowSeat showSeat) {

        return showSeatService.createShowSeat(showId, seatId, showSeat);
    }

    @GetMapping("/show/{showId}")
    public List<ShowSeat> getShowSeats(@PathVariable Long showId) {
        return showSeatService.getShowSeatsByShow(showId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowSeat> getById(@PathVariable Long id) {
        return showSeatService.getShowSeatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ShowSeat updateShowSeat(@PathVariable Long id, @RequestBody ShowSeat updated) {
        return showSeatService.updateShowSeat(id, updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowSeat(@PathVariable Long id) {
        showSeatService.deleteShowSeat(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ShowSeat updateStatus(
            @PathVariable Long id,
            @RequestParam ShowSeatStatus status) {
        return showSeatService.updateShowSeatStatus(id, status);
    }
}
