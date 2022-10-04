package com.example.kinoxpbackend.dto;


import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {
    String email;
    String phoneNumber;
    int employeeId;
    String safetyId;
    int screeningId;


}
