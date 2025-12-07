package org.example.bookmyshow_lld.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bookmyshow_lld.models.Ticket;

import java.util.Optional;

@Data
@AllArgsConstructor
public class BookSeatResponseDto {
    private boolean success;
    private String message;
    private Optional<Ticket> ticket;
}
