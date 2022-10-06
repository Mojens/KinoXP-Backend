package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.dto.ReservationResponse;
import com.example.kinoxpbackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation,Integer> {
    Reservation getReservationById(int id);

    Boolean existsBySafetyId(String safetyId);

    List<ReservationResponse> getReservationsByScreeningId(int id);
}
