package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}