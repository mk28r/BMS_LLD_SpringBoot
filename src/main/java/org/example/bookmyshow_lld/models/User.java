package org.example.bookmyshow_lld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;
@Entity
public class User extends BaseModel{
    private String Name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
