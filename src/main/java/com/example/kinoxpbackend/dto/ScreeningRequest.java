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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningRequest {

    private int id;
    private double performance;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Movie movie;
    private Theater theater;
    private List<Reservation> reservation;

    public static Screening getScreeningEntity(ScreeningRequest s) {
        return new Screening(s.id, s.performance, s.startTime, s.endTime, s.movie, s.theater, s.reservation);
    }

    public ScreeningRequest(Screening s) {
        this.id = s.getId();
        this.performance = s.getPerformance();
        this.startTime = s.getStartTime();
        this.endTime = s.getEndTime();
        this.movie = s.getMovie();
        this.theater = s.getTheater();
        this.reservation = s.getReservations();
    }


}
