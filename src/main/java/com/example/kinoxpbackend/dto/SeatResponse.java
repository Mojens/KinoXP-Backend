package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Seatings;
import com.example.kinoxpbackend.entity.Theater;
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
public class SeatResponse {

    int id;
    String rowNum;
    int seatNumber;
    private Theater theater;

    public SeatResponse(Seatings seatings){
        this.id = seatings.getId();
        this.rowNum = seatings.getRowNum();
        this.seatNumber = seatings.getSeatNumber();
        this.theater = seatings.getTheater();

    }
}
