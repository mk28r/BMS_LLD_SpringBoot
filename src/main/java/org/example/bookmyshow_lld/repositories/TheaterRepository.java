package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    public List<Theater> findByCityId(Long cityId);
}
