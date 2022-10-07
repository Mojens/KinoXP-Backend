package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.entity.Seatings;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.SeatingRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SeatServiceTest {

    @Autowired
    SeatingRepository seatingRepository;

    SeatService seatService;

    @BeforeEach
    public void initData(@Autowired SeatingRepository seatingRepository) {
        Theater t1 = new Theater(1);
        Seatings s1 = new Seatings("A", 2, t1);
        Seatings s2 = new Seatings("B", 20, t1);

        seatingRepository.save(s1);
        seatingRepository.save(s2);

        seatService = new SeatService(seatingRepository);
    }

    @Test
    void getAllSeats() {


    }

    @Test
    void getSeatById() {
    }

    @Test
    void getSeatsByTheaterId() {
    }
}