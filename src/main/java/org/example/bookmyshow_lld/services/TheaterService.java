package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.City;
import org.example.bookmyshow_lld.models.Theater;
import org.example.bookmyshow_lld.repositories.CityRepository;
import org.example.bookmyshow_lld.repositories.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService implements ITheaterService {

    private final TheaterRepository theaterRepository;
    private final CityRepository cityRepository;

    public TheaterService(TheaterRepository theaterRepository, CityRepository cityRepository) {
        this.theaterRepository = theaterRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Theater createTheater(Long cityId, Theater theater) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));

        theater.setCity(city);
        return theaterRepository.save(theater);
    }

    @Override
    public List<Theater> getTheatersByCity(Long cityId) {
        return theaterRepository.findByCityId(cityId);
    }

    @Override
    public Optional<Theater> getTheaterById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater updateTheater(Long id, Theater updated) {
        Theater existing = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        return theaterRepository.save(existing);
    }

    @Override
    public void deleteTheater(Long id) {
        if (!theaterRepository.existsById(id)) {
            throw new RuntimeException("Theater not found");
        }
        theaterRepository.deleteById(id);
    }
}
