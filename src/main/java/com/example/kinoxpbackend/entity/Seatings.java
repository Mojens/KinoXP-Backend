package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Seatings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column (length = 8, nullable = false)
    String rowNum;
    @Column (nullable = false)
    int seatNumber;

    @ManyToOne
    Theater theater;

    public Seatings(String row, int number, Theater theater){
        this.rowNum = row;
        this.seatNumber = number;
        this.theater = theater;
    }

    public Seatings(String row, int number) {
        this.rowNum = row;
        this.seatNumber = number;
    }

}
