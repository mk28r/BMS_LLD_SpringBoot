package org.example.bookmyshow_lld.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Auditorium extends BaseModel{
    private String name ;
    private int capacity;

    @ManyToOne
    private Theater theater ;

    @OneToMany(mappedBy = "auditorium")
    @JsonIgnore
    private List<Seat> seats ;

    @OneToMany(mappedBy = "auditorium")
    @JsonIgnore
    private List<Shows> show ;

}
