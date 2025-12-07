package org.example.bookmyshow_lld.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String name;
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Ticket> tickets;
}
