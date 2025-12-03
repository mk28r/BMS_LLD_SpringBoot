package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Movie;
import org.example.bookmyshow_lld.models.Shows;
import org.example.bookmyshow_lld.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    @Override
    public Optional<Movie> getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setName(updatedMovie.getName());
        movie.setDuration(updatedMovie.getDuration());

        return movieRepository.save(movie);
    }
    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    @Override
    public List<Shows> getShowsForMovie(Long movieId) {
        return List.of();
    }
}
