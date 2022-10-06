package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Seatings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatingRepository extends JpaRepository<Seatings, Integer> {

    boolean existsSeatingsById(int id);
    Seatings getSeatingsById(int id);
}
