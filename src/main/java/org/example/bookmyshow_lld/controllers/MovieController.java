package org.example.bookmyshow_lld.controllers;

import org.example.bookmyshow_lld.models.Movie;
import org.example.bookmyshow_lld.services.IMovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }
    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
