package org.example.bookmyshow_lld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseModel{
    private String name ;
    private int duration ;

    @OneToMany(mappedBy = "movie")
    private List<Shows> shows ;
}

