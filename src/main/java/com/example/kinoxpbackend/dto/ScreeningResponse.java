package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Theater;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningResponse {


    private int id;
    private double performance;
    // format json dateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
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

    public boolean hasScreenings (Movie movie, List<Screening> screenings) {
        for (Screening s : screenings) {
            if (s.getMovie().getId() == movie.getId()) {
                return true;

            }
        }
        return false;
    }


}
