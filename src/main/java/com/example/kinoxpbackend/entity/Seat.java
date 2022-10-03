package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column (length = 8, nullable = false)
    String row;
    @Column (nullable = false)
    int number;

    @ManyToOne()
    Theater theater;

    public Seat (String row, int number, Theater theater){
        this.row = row;
        this.number = number;
        this.theater = theater;
    }

}
