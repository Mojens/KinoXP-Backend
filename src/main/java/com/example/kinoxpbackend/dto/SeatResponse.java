package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Seatings;

public class SeatResponse {

    int id;
    String row;
    int number;

    public SeatResponse(Seatings seatings){
        this.id = seatings.getId();
        this.row = seatings.getRowNum();
        this.number = seatings.getSeatNumber();

    }
}
