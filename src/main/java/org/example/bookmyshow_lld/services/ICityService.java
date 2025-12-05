package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.City;

import java.util.List;
import java.util.Optional;

public interface ICityService {

    City createCity(City city);

    List<City> getAllCities();

    Optional<City> getCityById(Long id);

    City updateCity(Long id, City updatedCity);

    void deleteCity(Long id);
}