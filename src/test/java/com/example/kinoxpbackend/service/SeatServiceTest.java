package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.SeatResponse;
import com.example.kinoxpbackend.entity.Seatings;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.SeatingRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SeatServiceTest {

    @Autowired
    SeatingRepository seatingRepository;
    @Autowired
    TheaterRepository theaterRepository;
    SeatService seatService;

    SeatResponse staticSeatResponse;

    @BeforeEach
    public void initData(@Autowired SeatingRepository seatingRepository, @Autowired TheaterRepository theaterRepository) {
        Theater t1 = new Theater(1);
        theaterRepository.save(t1);
        Seatings s1 = new Seatings("A", 2, t1);
        Seatings s2 = new Seatings("B", 20, t1);



        seatingRepository.save(s1);
        seatingRepository.save(s2);

        staticSeatResponse = new SeatResponse(s2);

        seatService = new SeatService(seatingRepository);
    }

    @Test
    void getAllSeats() {
        List<SeatResponse> listOfSeats = seatService.getAllSeats();
        assertEquals(listOfSeats.size(), 2);
        assertInstanceOf(SeatResponse.class, listOfSeats.get(0));


    }

    @Test
    void getSeatById() throws Exception {
        SeatResponse seatResponse = seatService.getSeatById(2);
        assertEquals(seatResponse.getId(), staticSeatResponse.getId());
    }

    @Test
    void getSeatsByTheaterId() {
        List<SeatResponse> listOfSeatResponses = seatService.getSeatsByTheaterId(1);
        assertEquals(listOfSeatResponses.size(), 2);

    }
}