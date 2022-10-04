package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.SeatChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatChoiceRepository extends JpaRepository<SeatChoice, Integer> {
    SeatChoice findSeatChoiceById(int id);
}
