package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
