package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Movie;
import org.example.bookmyshow_lld.models.Shows;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();
    Optional<Movie> getMovieById(Long movieId);
    Movie updateMovie(Long id, Movie updatedMovie);
    void deleteMovie(Long id);
    List<Shows> getShowsForMovie(Long movieId);
}
