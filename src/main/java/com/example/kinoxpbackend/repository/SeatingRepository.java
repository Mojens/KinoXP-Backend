package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.dto.SeatResponse;
import com.example.kinoxpbackend.entity.Seatings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatingRepository extends JpaRepository<Seatings, Integer> {

    boolean existsSeatingsById(int id);
    Seatings getSeatingById(int id);

    SeatResponse getSeatingsById(int id);


}
