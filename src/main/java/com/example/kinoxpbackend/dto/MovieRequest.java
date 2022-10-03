package com.example.kinoxpbackend.dto;


import com.example.kinoxpbackend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private double duration;
    private int ageLimit;
    private LocalDate showStartDate;
    private LocalDate showEndDate;

    public static Movie getMovieEntity(MovieRequest m){
        return new Movie(m.id,m.title,m.description,m.rating,m.genre,m.duration,m.ageLimit,m.showStartDate,m.showEndDate);
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
        this.showStartDate = m.getShowStartDate();
        this.showEndDate = m.getShowEndDate();
    }
}
