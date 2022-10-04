package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.SeatChoice;
import com.example.kinoxpbackend.entity.Seatings;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatChoiceResponse {

    private int id;

    private int seatingsId;

    private int reservationId;

    public SeatChoiceResponse(SeatChoice seatChoice) {
        this.id = seatChoice.getId();
        this.seatingsId = seatChoice.getSeatings().getId();
        this.reservationId = seatChoice.getReservation().getId();
    }
}
