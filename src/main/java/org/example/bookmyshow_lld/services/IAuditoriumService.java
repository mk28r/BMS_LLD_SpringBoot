package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Auditorium;
import org.example.bookmyshow_lld.models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAuditoriumService {
    public Auditorium createAuditorium(Long theaterId, Auditorium auditorium) ;
    public List<Auditorium> getAuditoriumsByTheater(Long theaterId) ;
    public Auditorium updateAuditorium(Long id, Auditorium updated) ;
    public void deleteAuditorium(Long id) ;
}
