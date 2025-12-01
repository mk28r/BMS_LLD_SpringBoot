package org.example.bookmyshow_lld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    private String seatNumber;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    private int rowVal ;
    private int columnVal ;

    @ManyToOne
    private Auditorium auditorium;
}
