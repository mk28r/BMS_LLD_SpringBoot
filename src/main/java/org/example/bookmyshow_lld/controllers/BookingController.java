package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.dto.*;
import org.example.bookmyshow_lld.models.Ticket;
import org.example.bookmyshow_lld.services.IBookingService;
import org.example.bookmyshow_lld.services.RedisBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final IBookingService bookingService;

    public BookingController(RedisBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/block")
    public ResponseEntity<BlockSeatsResponseDto> blockSeats(
            @RequestBody BlockSeatsRequestDto req
    ) {
        boolean blocked = bookingService.blockSeats(
                req.getShowId(),
                req.getSeatId(),
                req.getUserId()
        );

        BlockSeatsResponseDto res = new BlockSeatsResponseDto();
        res.setSuccess(blocked);
        res.setMessage(blocked ? "Seats blocked successfully" : "Failed to block seats");

        return ResponseEntity.ok(res);
    }


    @PostMapping("/confirm")
    public ResponseEntity<BookSeatResponseDto> confirmBooking(
            @RequestBody BookSeatRequestDto req
    ) {
        Optional<Ticket> ticket = bookingService.bookTicket(
                req.getShowId(),
                req.getSeatId(),
                req.getUserId()
        );

        if (ticket.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new BookSeatResponseDto(false, "Booking failed", null)
            );
        }

        return ResponseEntity.ok(
                new BookSeatResponseDto(true, "Booking successful", ticket)
        );
    }

    @DeleteMapping("/locks")
    public ResponseEntity<ApiResponseDto> clearSeatLocks() {
        bookingService.clearAllSeatLocks();
        return ResponseEntity.ok(new ApiResponseDto(
                true,
                "All seat locks cleared"
        ));
    }
}
