package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.ShowSeat;
import org.example.bookmyshow_lld.models.ShowSeatStatus;

import java.util.List;
import java.util.Optional;

public interface IShowSeatService {

    ShowSeat createShowSeat(Long showId, Long seatId, ShowSeat showSeat);

    List<ShowSeat> getShowSeatsByShow(Long showId);

    Optional<ShowSeat> getShowSeatById(Long id);

    ShowSeat updateShowSeat(Long id, ShowSeat updatedShowSeat);

    void deleteShowSeat(Long id);

    // Booking/helpers
    ShowSeat updateShowSeatStatus(Long id, ShowSeatStatus status);

    List<ShowSeat> updateSeatsStatus(List<Long> seatIds, ShowSeatStatus status);
}
