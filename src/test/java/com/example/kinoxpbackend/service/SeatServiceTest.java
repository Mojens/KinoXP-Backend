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


   public static SeatingRepository seatingRepository;

   public static TheaterRepository theaterRepository;
    public SeatService seatService;

    static SeatResponse staticSeatResponse;
    static SeatResponse staticSeatResponse2;

    static List<SeatResponse> listOfSeatsInTheater1;

    static int sizeOfRepo;

    @BeforeAll
    public static void initData(@Autowired SeatingRepository seating_Repository, @Autowired TheaterRepository theater_Repository) {
        theaterRepository = theater_Repository;
        seatingRepository = seating_Repository;

        Theater t1 = new Theater(1);
        theaterRepository.save(t1);
        Seatings s1 = new Seatings("A", 2, t1);
        Seatings s2 = new Seatings("B", 20, t1);

        seatingRepository.save(s1);
        seatingRepository.save(s2);



        sizeOfRepo = seatingRepository.findAll().size();

        staticSeatResponse = new SeatResponse(seatingRepository.getSeatingById(1));
        staticSeatResponse2 = new SeatResponse(seatingRepository.getSeatingById(2));

        listOfSeatsInTheater1 = seatingRepository.getSeatingsByTheaterId(1);
    }

    @BeforeEach
    public void setSeatServiceUp(){
        seatService = new SeatService(seatingRepository);
    }

    @Test
    void getAllSeats() {
        seatService.getAllSeats();
        assertEquals(sizeOfRepo , seatService.getAllSeats().size());
        assertInstanceOf(SeatResponse.class, seatService.getAllSeats().get(0));


    }

    @Test
    void getSeatById() throws Exception {
        SeatResponse seatResponse = seatService.getSeatById(1);
        assertEquals(seatResponse.getId(), staticSeatResponse.getId());
        assertNotEquals(seatResponse.getId(), staticSeatResponse2.getId());
    }

    @Test
    void getSeatsByTheaterId() {
        assertEquals(listOfSeatsInTheater1.size() ,seatService.getSeatsByTheaterId(1).size());

    }
}