package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Auditorium;
import org.example.bookmyshow_lld.models.Movie;
import org.example.bookmyshow_lld.models.Shows;
import org.example.bookmyshow_lld.repositories.AuditoriumRepository;
import org.example.bookmyshow_lld.repositories.MovieRepository;
import org.example.bookmyshow_lld.repositories.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService implements IShowService {

    private final ShowRepository showRepo;
    private final MovieRepository movieRepo;
    private final AuditoriumRepository auditoriumRepo;

    public ShowService(ShowRepository showRepo, MovieRepository movieRepo, AuditoriumRepository auditoriumRepo) {
        this.showRepo = showRepo;
        this.movieRepo = movieRepo;
        this.auditoriumRepo = auditoriumRepo;
    }

    @Override
    public Shows createShow(Long auditoriumId, Long movieId, Shows show) {
        // check for collision in timing of show
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Auditorium auditorium = auditoriumRepo.findById(auditoriumId)
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        show.setMovie(movie);
        show.setAuditorium(auditorium);

        return showRepo.save(show);
    }

    @Override
    public List<Shows> getShowsByAuditorium(Long auditoriumId) {
        return showRepo.findByAuditoriumId(auditoriumId);
    }

    @Override
    public Shows updateShow(Long id, Shows updated) {
        Shows s = showRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        s.setStartDate(updated.getStartDate());
        s.setEndDate(updated.getEndDate());
        return showRepo.save(s);
    }

    @Override
    public void deleteShow(Long id) {
        showRepo.deleteById(id);
    }
}
