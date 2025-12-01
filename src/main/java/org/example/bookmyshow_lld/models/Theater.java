package org.example.bookmyshow_lld.models;

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
    private String Name ;
    private String address ;
    @ManyToOne
    protected City city ;

    @OneToMany(mappedBy ="theater" )
    private List<Auditorium> auditoriums ;
}
