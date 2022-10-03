package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Seatings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seatings, Integer> {
}
