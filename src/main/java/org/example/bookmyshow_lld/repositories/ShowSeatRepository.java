package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.example.bookmyshow_lld.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findByShowsId(Long showId);

    List<ShowSeat> findBySeatId(Long seatId);
}

