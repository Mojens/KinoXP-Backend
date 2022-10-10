package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatChoiceRequest {
    private int id;

    private int seatingsId;

    private int reservationId;

   
}
