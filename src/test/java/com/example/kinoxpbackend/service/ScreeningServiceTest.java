package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.ScreeningRequest;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.dto.SeatResponse;
import com.example.kinoxpbackend.dto.ShiftResponse;
import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ReservationRepository;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScreeningServiceTest {


    static ScreeningRepository screeningRepository;


    static MovieRepository movieRepository;


    static TheaterRepository theaterRepository;


    static ReservationRepository reservationRepository;

    static ScreeningService screeningService;

    static Screening sc1;
    static Screening sc2;
    static Screening sc3;

    static int sizeOfRepo;

    @BeforeAll
    public static void initData(@Autowired ScreeningRepository screening_Repository, @Autowired MovieRepository movie_Repository, @Autowired TheaterRepository theater_Repository, @Autowired ReservationRepository reservation_Repository ) {
        screeningRepository = screening_Repository;
        movieRepository = movie_Repository;
        theaterRepository = theater_Repository;
        reservationRepository = reservation_Repository;

        Movie movie1 = new Movie(1,"Batmand", "Sej film", 10, "Action", "2 timer", "pg 13", 100, LocalDate.of(2022, 9,1), LocalDate.of(2022, 12,1));
        Movie movie2 = new Movie(2,"Hello kitty", "sød film", 2, "Romance", "1 time", "pg 7", 50, LocalDate.of(2023, 1,1), LocalDate.of(2023, 3,1));
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Theater theater1 = new Theater(1);
        Theater theater2 = new Theater(2);
        theaterRepository.save(theater1);
        theaterRepository.save(theater2);

        Screening screening1 = new Screening(1,10, LocalDateTime.of(2022,9,9,10,10), LocalDateTime.of(2022,9,9,11,50), movie1, theater1);
        Screening screening2 = new Screening(2,20, LocalDateTime.of(2022,9,9,10,10), LocalDateTime.of(2022,9,9,11,50), movie2,  theater2);
        Screening screening3 = new Screening(120, LocalDateTime.of(2022,9,9,10,10), LocalDateTime.of(2022,9,9,11,50), movie2,  theater2);
        screeningRepository.save(screening1);
        screeningRepository.save(screening2);

        Reservation res1 = Reservation.builder()
                .id(1)
                .email("jan")
                .phoneNumber("61426729")
                .employeeId(1)
                .safetyId("h")
                .screening(screening1)
                .build();
        Reservation res2 = Reservation.builder()
                .id(2)
                .email("Bo")
                .phoneNumber("61426786")
                .employeeId(2)
                .safetyId("ui")
                .screening(screening2)
                .build();
        Reservation res3 = Reservation.builder()
                .id(3)
                .email("Lars")
                .phoneNumber("61426700")
                .employeeId(1)
                .safetyId("a")
                .screening(screening2)
                .build();

        reservationRepository.save(res1);
        reservationRepository.save(res2);
        reservationRepository.save(res3);

        sc1 = screening1;
        sc2 = screening2;
        sc3 = screening3;

        screeningService = new ScreeningService(screeningRepository, movieRepository, theaterRepository, reservationRepository);


        if (screeningRepository.findAll().size() > 2) {
            screeningRepository.deleteById(3);
        }
        sizeOfRepo = screeningRepository.findAll().size();
    }


    @Test
    void getAllScreenings() {
        List<ScreeningResponse> screenings = screeningService.getAllScreenings();
        assertEquals(sizeOfRepo, screenings.size());
        assertNotEquals(sizeOfRepo + 1, screenings.size());
    }

    @Test
    void getScreeningById() {
        ScreeningResponse screeningResponse = screeningService.getScreeningById(1);
        assertEquals(screeningResponse.getId(), sc1.getId());
        assertNotEquals(screeningResponse.getId(), sc2.getId());
    }

    @Test
    void addScreening() {
        ScreeningRequest screeningRequest = new ScreeningRequest(sc3);
        screeningService.addScreening(screeningRequest);
        List<ScreeningResponse> screeningResponses = screeningService.getAllScreenings();
        assertEquals(sizeOfRepo +1, screeningResponses.size());
        assertEquals(120, screeningResponses.get(2).getPerformance());
        assertNotEquals(sizeOfRepo, screeningResponses.size());
    }

    @Test
    void editScreening() {
        ScreeningRequest screeningRequest = new ScreeningRequest(sc3);
        screeningService.editScreening(screeningRequest, 1);
        List<ScreeningResponse> screeningResponses = screeningService.getAllScreenings();
        assertEquals(120, screeningResponses.get(0).getPerformance());

    }

    @Test
    void deleteScreening() {
        screeningService.deleteScreening(2);
        List<ScreeningResponse> screeningResponses = screeningService.getAllScreenings();
        assertEquals(sizeOfRepo -1, screeningResponses.size());
        assertEquals(10, screeningResponses.get(0).getPerformance());
    }
}