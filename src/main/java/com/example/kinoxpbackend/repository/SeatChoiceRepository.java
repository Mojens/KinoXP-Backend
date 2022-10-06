package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.dto.SeatChoiceResponse;
import com.example.kinoxpbackend.entity.SeatChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatChoiceRepository extends JpaRepository<SeatChoice, Integer> {
    SeatChoice findSeatChoiceById(int id);

    List<SeatChoiceResponse> getSeatChoicesByReservationId(int id);
}
