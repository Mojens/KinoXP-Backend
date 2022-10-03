package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository <Reservation,Integer> {
}
