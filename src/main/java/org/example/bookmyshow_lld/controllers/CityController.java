package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.models.City;
import org.example.bookmyshow_lld.services.CityService;
import org.example.bookmyshow_lld.services.ICityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private final ICityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        return cityService.getCityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
