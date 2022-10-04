package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 450)
    private String title;
    @Column(nullable = false,length = 450)
    private String description;
    @Column(nullable = false,length = 450)
    private double rating;
    @Column(nullable = false,length = 450)
    private String genre;
    @Column(nullable = false,length = 450)
    private double duration;
    @Column(nullable = false,length = 450)

    private int ageLimit;
    @Column(nullable = false,length = 450)
    private double price;


    @Column(nullable = false,length = 450)
    private LocalDate showStartDate;
    @Column(nullable = false,length = 450)
    private LocalDate showEndDate;

    @OneToMany(mappedBy = "movie")
    List<Screening> screenings = new ArrayList<>();

    public Movie() {
    }

    public Movie(int id, String title, String description, double rating, String genre, double duration, int ageLimit,double price, LocalDate showStartDate, LocalDate showEndDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
        this.duration = duration;
        this.ageLimit = ageLimit;
        this.price = price;
        this.showStartDate = showStartDate;
        this.showEndDate = showEndDate;

    }
    public Movie( String title, String description, double rating, String genre, double duration, int ageLimit,double price, LocalDate showStartDate, LocalDate showEndDate) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
        this.duration = duration;
        this.ageLimit = ageLimit;
        this.price = price;
        this.showStartDate = showStartDate;
        this.showEndDate = showEndDate;

    }
}
