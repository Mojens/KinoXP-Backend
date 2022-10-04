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
    int id;
    String email;
    String phoneNumber;
    int employeeId;
    String safetyId;
    Screening screening;

    public static Reservation getReservationEntity(ReservationRequest r){
        return new Reservation(r.email, r.phoneNumber, r.employeeId, r.safetyId, r.screening);

    }

    public ReservationRequest(Reservation r){
        this.id = r.getId();
        this.email = r.getEmail();
        this.phoneNumber = r.getPhoneNumber();
        this.employeeId = r.getEmployeeId();
        this.safetyId = r.getSafetyId();
        this.screening = r.getScreening();
    }


}
