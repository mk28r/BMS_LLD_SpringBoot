package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.models.Theater;
import org.example.bookmyshow_lld.services.ITheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theaters")
public class TheaterController {

    private final ITheaterService theaterService;

    public TheaterController(ITheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/city/{cityId}")
    public Theater createTheater(@PathVariable Long cityId, @RequestBody Theater theater) {
        return theaterService.createTheater(cityId, theater);
    }

    @GetMapping("/city/{cityId}")
    public List<Theater> getTheatersByCity(@PathVariable Long cityId) {
        return theaterService.getTheatersByCity(cityId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        return theaterService.getTheaterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Theater updateTheater(@PathVariable Long id, @RequestBody Theater updatedTheater) {
        return theaterService.updateTheater(id, updatedTheater);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }
}
