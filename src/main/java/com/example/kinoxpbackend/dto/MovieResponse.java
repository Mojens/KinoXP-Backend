package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Screening;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    private String duration;
    private String ageLimit;
    private double price;
    private String photo;
    private String stars;
    private String trailers;
    private LocalDate showStartDate;
    private LocalDate showEndDate;



    private List<ScreeningResponse> screeningResponse;


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
        this.stars = m.getStars();
        this.trailers = m.getTrailers();
        this.showStartDate = m.getShowStartDate();
        this.showEndDate = m.getShowEndDate();
        if (m.getScreenings().size() > 0) {
           screeningResponse = m.getScreenings().stream().map(screenings -> ScreeningResponse.builder()
                    .id(screenings.getId())
                    .movieId(screenings.getMovie().getId())
                   .startTime(screenings.getStartTime())
                     .endTime(screenings.getEndTime())
                   .performance(screenings.getPerformance())
                   .theaterId(screenings.getTheater().getId())
                   .build()).collect(Collectors.toList());
        }
    }



}
