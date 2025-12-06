package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.models.Auditorium;
import org.example.bookmyshow_lld.services.IAuditoriumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditoriums")
public class AuditoriumController {

    private final IAuditoriumService auditoriumService;

    public AuditoriumController(IAuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @PostMapping("/theater/{theaterId}")
    public Auditorium create(@PathVariable Long theaterId, @RequestBody Auditorium auditorium) {
        return auditoriumService.createAuditorium(theaterId, auditorium);
    }

    @GetMapping("/theater/{theaterId}")
    public List<Auditorium> getByTheater(@PathVariable Long theaterId) {
        return auditoriumService.getAuditoriumsByTheater(theaterId);
    }

    @GetMapping("/{id}")
    public List<Auditorium> getById(@PathVariable Long id) {
        return auditoriumService.getAuditoriumsByTheater(id);
    }

    @PutMapping("/{id}")
    public Auditorium update(@PathVariable Long id, @RequestBody Auditorium auditorium) {
        return auditoriumService.updateAuditorium(id, auditorium);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        auditoriumService.deleteAuditorium(id);
        return ResponseEntity.noContent().build();
    }
}
