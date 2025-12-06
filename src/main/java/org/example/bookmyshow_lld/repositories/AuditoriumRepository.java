package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
    List<Auditorium> findByTheaterId(Long theaterId);
}
