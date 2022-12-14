package com.example.kinoxpbackend.dto;


import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Screening;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieRequest {

    private int id;
    private String title;
    private String description;
    private double rating;
    private String genre;
    private String duration;
    private String ageLimit;
    private double price;
    private String photo;
    private String stars;
    private String trailers;
    private LocalDate showStartDate;
    private LocalDate showEndDate;



    public static Movie getMovieEntity(MovieRequest m){
        return new Movie(m.id,m.title,m.description,m.rating,m.genre,m.duration,m.ageLimit,m.price,m.photo,m.stars,m.trailers,m.showStartDate,m.showEndDate);
    }

    // Movie to MovieRequest conversion
    public MovieRequest(Movie m){
        this.id = m.getId();
        this.title = m.getTitle();
        this.description = m.getDescription();
        this.rating = m.getRating();
        this.genre = m.getGenre();
        this.duration = m.getDuration();
        this.ageLimit = m.getAgeLimit();
        this.price = m.getPrice();
        this.showStartDate = m.getShowStartDate();
        this.showEndDate = m.getShowEndDate();

    }
}
