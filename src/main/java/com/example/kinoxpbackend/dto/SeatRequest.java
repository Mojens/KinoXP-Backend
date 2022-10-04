package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Seatings;
import com.example.kinoxpbackend.entity.Theater;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatRequest {

    int id;
    String rowNum;
    int seatNumber;
    private Theater theater;

    public static Seatings getSeatingEntity(SeatRequest s){
        return new Seatings(s.id, s.rowNum, s.seatNumber, s.theater);

    }

    public SeatRequest(Seatings seatings){
        this.id = seatings.getId();
        this.rowNum = seatings.getRowNum();
        this.seatNumber = seatings.getSeatNumber();
        this.theater = seatings.getTheater();

    }


}
