package org.example.bookmyshow_lld.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Theater extends BaseModel{
    private String name ;
    private String address ;
    @ManyToOne
    private City city ;

    @OneToMany(mappedBy ="theater" )
    @JsonIgnore
    private List<Auditorium> auditoriums ;
}
