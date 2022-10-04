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

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningResponse {


    private int id;
    private double performance;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Movie movie;
    private Theater theater;
    private List<Reservation> reservation;

    public ScreeningResponse(Screening screening) {
        this.id = screening.getId();
        this.performance = screening.getPerformance();
        this.startTime = screening.getStartTime();
        this.endTime = screening.getEndTime();
        this.movie = screening.getMovie();
        this.theater = screening.getTheater();
        this.reservation = screening.getReservations();
    }
}
