package org.example.bookmyshow_lld.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Shows extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    private Auditorium auditorium ;

    @OneToMany(mappedBy = "shows")
    @JsonIgnore
    private List<ShowSeat> Showseats ;
}
