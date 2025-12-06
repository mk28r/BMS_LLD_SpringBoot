package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.models.Shows;
import org.example.bookmyshow_lld.services.IShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowController {

    private final IShowService showService;

    public ShowController(IShowService showService) {
        this.showService = showService;
    }

    @PostMapping("/auditorium/{auditoriumId}/movie/{movieId}")
    public Shows createShow(
            @PathVariable Long auditoriumId,
            @PathVariable Long movieId,
            @RequestBody Shows show) {
        return showService.createShow(auditoriumId, movieId, show);
    }

    @GetMapping("/auditorium/{auditoriumId}")
    public List<Shows> getByAuditorium(@PathVariable Long auditoriumId) {
        return showService.getShowsByAuditorium(auditoriumId);
    }

    @GetMapping("/{id}")
    public List<Shows> getShow(@PathVariable Long id) {
        return showService.getShowsByAuditorium(id);
    }

    @PutMapping("/{id}")
    public Shows update(@PathVariable Long id, @RequestBody Shows show) {
        return showService.updateShow(id, show);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}
