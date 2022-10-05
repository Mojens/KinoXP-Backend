package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Theater;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningRequest {

    private int id;
    private double performance;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int movieId;
    private int theaterId;

    public ScreeningRequest(Screening screening) {
        this.id = screening.getId();
        this.performance = screening.getPerformance();
        this.startTime = screening.getStartTime();
        this.endTime = screening.getEndTime();
        this.movieId = screening.getMovie().getId();
        this.theaterId = screening.getTheater().getId();
    }

    public static Screening getScreeningEntity(ScreeningRequest s){
        return Screening.builder()
                .id(s.getId())
                .performance(s.getPerformance())
                .startTime(s.getStartTime())
                .endTime(s.getEndTime())
                .movie(Movie.builder().id(s.getMovieId()).build())
                .theater(Theater.builder().id(s.getTheaterId()).build())
                .build();
    }


}
