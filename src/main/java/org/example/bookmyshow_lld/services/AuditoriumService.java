package org.example.bookmyshow_lld.services;

import org.example.bookmyshow_lld.models.Auditorium;
import org.example.bookmyshow_lld.models.Theater;
import org.example.bookmyshow_lld.repositories.AuditoriumRepository;
import org.example.bookmyshow_lld.repositories.TheaterRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumService implements IAuditoriumService {

    private final AuditoriumRepository auditoriumRepo;
    private final TheaterRepository theaterRepo;

    public AuditoriumService(AuditoriumRepository auditoriumRepo, TheaterRepository theaterRepo) {
        this.auditoriumRepo = auditoriumRepo;
        this.theaterRepo = theaterRepo;
    }

    @Override
    public Auditorium createAuditorium(Long theaterId, Auditorium auditorium) {
        Theater theater = theaterRepo.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        auditorium.setTheater(theater);
        return auditoriumRepo.save(auditorium);
    }

    @Override
    public List<Auditorium> getAuditoriumsByTheater(Long theaterId) {
        return auditoriumRepo.findByTheaterId(theaterId);
    }

    @Override
    public Auditorium updateAuditorium(Long id, Auditorium updated) {
        Auditorium a = auditoriumRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        a.setName(updated.getName());
        a.setCapacity(updated.getCapacity());
        return auditoriumRepo.save(a);
    }

    @Override
    public void deleteAuditorium(Long id) {
        auditoriumRepo.deleteById(id);
    }
}
