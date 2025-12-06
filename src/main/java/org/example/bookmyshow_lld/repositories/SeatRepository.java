package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByAuditoriumId(Long id);
}
