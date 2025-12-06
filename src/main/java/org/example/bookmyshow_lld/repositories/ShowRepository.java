package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Shows, Long> {

    Optional<Shows> findById(long id);
    List<Shows> findByAuditoriumId(long auditoriumId);

}