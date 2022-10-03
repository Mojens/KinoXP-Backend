package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@NoArgsConstructor

@JsonInclude (JsonInclude.Include.NON_NULL)

public class ReservationResponse {

    int id;
    String email;
    String phoneNumber;
    int employeeId;
    String safetyId;

    public ReservationResponse (Reservation r){
        this.id = r.getId();
        this.email = r.getEmail();
        this.phoneNumber = r.getPhoneNumber();
        this.employeeId = r.getEmployeeId();
        this.safetyId = r.getSafetyId();
    }

}
