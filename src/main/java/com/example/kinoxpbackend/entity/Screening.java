package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder

public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 450, columnDefinition = "decimal(10,2) default '0.0'")
    // default value
    private double performance;
    @Column(nullable = false, length = 450)
    private LocalDateTime startTime;
    @Column(nullable = false, length = 450)
    private LocalDateTime endTime;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    public Screening(int id, double performance, LocalDateTime startTime, LocalDateTime endTime, Movie movie, Theater theater, List<Reservation> reservations) {
        this.id = id;
        this.performance = performance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.theater = theater;
        this.reservations = reservations;
    }

    public Screening(double performance, LocalDateTime startTime, LocalDateTime endTime, Movie movie, Theater theater) {
        this.performance = performance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.theater = theater;
    }

    public Screening() {
    }


    public Screening(int id, double performance, LocalDateTime startTime, LocalDateTime endTime, Movie movie, Theater theater) {
        this.id = id;
        this.performance = performance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.theater = theater;

    }
}
