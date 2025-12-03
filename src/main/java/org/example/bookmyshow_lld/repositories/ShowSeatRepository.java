package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

//    List<ShowSeat> findAllByShowIdAndSeatIdIn(Long showId, List<Long> seatIds);
//
//    @Modifying
//    @Query("UPDATE ShowSeat s SET s.ticket = :ticket, s.status = 1 WHERE s.id IN :ids")
//    int bookShowSeatsBulk(@Param("ids") List<Long> ids, @Param("ticket") Ticket ticket);
}
