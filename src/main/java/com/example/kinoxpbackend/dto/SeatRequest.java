package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Seatings;

public class SeatRequest {
    int id;
    String row;
    int number;


    public SeatRequest(Seatings seatings){
        this.id = seatings.getId();
        this.row = seatings.getRowNum();
        this.number = seatings.getSeatNumber();

    }


}
