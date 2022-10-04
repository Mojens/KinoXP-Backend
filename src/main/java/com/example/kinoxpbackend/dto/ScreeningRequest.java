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

    public ScreeningRequest(double performance, LocalDateTime startTime, LocalDateTime endTime, int movieId, int theaterId) {
        this.performance = performance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieId = movieId;
        this.theaterId = theaterId;
    }


}
