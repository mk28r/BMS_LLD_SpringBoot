package org.example.bookmyshow_lld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class City extends BaseModel{
    String name ;

    @OneToMany(mappedBy = "city")
    List<Theater> threaters ;
}
