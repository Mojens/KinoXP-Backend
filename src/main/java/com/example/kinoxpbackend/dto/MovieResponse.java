package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {

    private int id;
    private String title;
    private String description;
    private double rating;
    private String genre;
    private double duration;
    private String ageLimit;
    private double price;
    private String photo;
    private LocalDate showStartDate;
    private LocalDate showEndDate;

    public MovieResponse(Movie m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.description = m.getDescription();
        this.rating = m.getRating();
        this.genre = m.getGenre();
        this.duration = m.getDuration();
        this.ageLimit = m.getAgeLimit();
        this.price = m.getPrice();
        this.photo = m.getPhoto();
        this.showStartDate = m.getShowStartDate();
        this.showEndDate = m.getShowEndDate();
    }

}
