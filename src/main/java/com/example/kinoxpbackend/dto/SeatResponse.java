package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Seat;
import com.example.kinoxpbackend.entity.Theater;

public class SeatResponse {

    int id;
    String row;
    int number;

    Theater theater;

    public SeatResponse(Seat seat){
        this.id = seat.getId();
        this.row = seat.getRow();
        this.number = seat.getNumber();
        this.theater = seat.getTheater();
    }
}
