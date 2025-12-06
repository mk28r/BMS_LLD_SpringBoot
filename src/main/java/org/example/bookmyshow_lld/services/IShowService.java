package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Auditorium;
import org.example.bookmyshow_lld.models.Movie;
import org.example.bookmyshow_lld.models.Shows;

import java.util.List;

public interface IShowService {
    public Shows createShow(Long auditoriumId, Long movieId, Shows show) ;
    public List<Shows> getShowsByAuditorium(Long auditoriumId) ;
    public Shows updateShow(Long id, Shows updated) ;
    public void deleteShow(Long id) ;
}
