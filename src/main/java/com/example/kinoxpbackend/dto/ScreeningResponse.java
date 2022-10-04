package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Theater;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningResponse {


    private int id;
    private double performance;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int movieId;
    private int theaterId;

    public ScreeningResponse(Screening screening) {
        this.id = screening.getId();
        this.performance = screening.getPerformance();
        this.startTime = screening.getStartTime();
        this.endTime = screening.getEndTime();
        this.movieId = screening.getMovie().getId();
        this.theaterId = screening.getTheater().getId();
    }
}
