package org.example.bookmyshow_lld.repositories;

import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.example.bookmyshow_lld.models.ShowSeat;
import org.example.bookmyshow_lld.models.ShowSeatStatus;
import org.example.bookmyshow_lld.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import org.example.bookmyshow_lld.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    // Correct usage of field names: shows.id and seat.id
    List<ShowSeat> findByShowsId(Long showId);

    List<ShowSeat> findAllByShowsIdAndSeatIdIn(Long showId, List<Long> seatIds);

    @Modifying
    @Transactional
    @Query("""
           UPDATE ShowSeat s
           SET s.ticket = :ticket,
               s.showSeatStatus = :status
           WHERE s.id IN :ids
           """)
    void bookShowSeatsBulk(@Param("ids") List<Long> ids,
                           @Param("ticket") Ticket ticket,
                           @Param("status") ShowSeatStatus status);
}
