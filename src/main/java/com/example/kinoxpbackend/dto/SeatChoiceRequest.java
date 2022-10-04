package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.SeatChoice;
import com.example.kinoxpbackend.entity.Seatings;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatChoiceRequest {
    private int id;

    private Seatings seatings;

    private Reservation reservation;

    public static SeatChoice getSeatChoice (SeatChoiceRequest s) {
        return new SeatChoice(s.id, s.seatings, s.reservation);
    }


}
