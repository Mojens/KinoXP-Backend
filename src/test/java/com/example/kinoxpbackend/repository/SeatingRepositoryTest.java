package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.dto.SeatResponse;
import com.example.kinoxpbackend.entity.Seatings;
import com.example.kinoxpbackend.entity.Theater;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SeatingRepositoryTest {

    @Autowired
    SeatingRepository seatingRepository;

    @Autowired
    TheaterRepository theaterRepository;

    static Seatings seat1;

    static Seatings seat2;

    static Seatings seat3;

    @BeforeAll
    public static void setUpData(@Autowired SeatingRepository seatingRepository, @Autowired TheaterRepository theaterRepository) {
        Theater t1 = new Theater(1);
        theaterRepository.save(t1);
        Seatings s1 = new Seatings("A", 2, t1);
        Seatings s2 = new Seatings("B", 20, t1);

        seatingRepository.save(s1);
        seatingRepository.save(s2);


        seat1 = s1;
        seat2 = s2;
        seat3 = s2;
        seatingRepository.save(seat3);
    }

    @Test
    void existsSeatingsById() {
        assertTrue(seatingRepository.existsSeatingsById(1));
    }

    @Test
    void getSeatingById() {
        int idFromRepo = seatingRepository.getSeatingById(1).getId();
        int idFromStatic = seat1.getId();
        assertEquals(idFromRepo, idFromStatic);
    }


    @Test
    void getSeatingsById() {
        SeatResponse seatingResponseFromRepo = seatingRepository.getSeatingsById(1);
        assertEquals(seatingResponseFromRepo.getId(), seat1.getId());
        assertNotEquals(seatingResponseFromRepo.getId(), seat2.getId());
    }



    @Test
    void getSeatingsByTheaterId() {
        List<SeatResponse> listOfSeatings = seatingRepository.getSeatingsByTheaterId(1);
        assertEquals(listOfSeatings.size(), 2);
    }

}