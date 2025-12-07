package org.example.bookmyshow_lld.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Ticket extends BaseModel{
    private int amount;

    @ManyToOne
    private User user ;


    @ManyToOne
    private Shows show;

    @OneToMany(mappedBy = "ticket")
    @JsonIgnore
    private List<ShowSeat> showSeat;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status;
}
