package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Theater;

import java.util.List;
import java.util.Optional;

public interface ITheaterService {

    Theater createTheater(Long cityId, Theater theater);
    List<Theater> getTheatersByCity(Long cityId);
    Optional<Theater> getTheaterById(Long id);
    Theater updateTheater(Long id, Theater updatedTheater);
    void deleteTheater(Long id);
}
