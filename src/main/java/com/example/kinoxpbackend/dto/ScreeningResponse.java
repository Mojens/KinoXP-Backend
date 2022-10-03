package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
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

    public ScreeningResponse(int id, double performance, LocalDateTime startTime, LocalDateTime endTime, Movie movie, Theater theater, List<Reservation> reservation) {
        this.id = id;
        this.performance = performance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.theater = theater;
        this.reservation = reservation;
    }
}
