package com.example.kinoxpbackend.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SeatChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Seatings seatings;

    @ManyToOne
    private Reservation reservation;


    public SeatChoice(int id, Seatings seatings, Reservation reservation) {
        this.id = id;
        this.seatings = seatings;
        this.reservation = reservation;
    }

    public SeatChoice(Seatings seatings, Reservation reservation) {
        this.seatings = seatings;
        this.reservation = reservation;
    }

    public SeatChoice() {

    }
}
