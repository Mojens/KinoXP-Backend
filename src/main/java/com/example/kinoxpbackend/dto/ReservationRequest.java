package com.example.kinoxpbackend.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor

public class ReservationRequest {
    int id;
    String email;
    String phoneNumber;
    int employeeId;
    String safetyId;




}
