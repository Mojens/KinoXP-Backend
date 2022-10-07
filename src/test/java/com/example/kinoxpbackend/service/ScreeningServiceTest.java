package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.ScreeningRequest;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.dto.SeatResponse;
import com.example.kinoxpbackend.dto.ShiftResponse;
import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScreeningServiceTest {

    @Autowired
    ScreeningRepository screeningRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    ScreeningService screeningService;

    Screening sc1;
    Screening sc2;
    Screening sc3;

    @BeforeEach
    public void initData(@Autowired ScreeningRepository screeningRepository, @Autowired MovieRepository movieRepository, @Autowired TheaterRepository theaterRepository ) {
        Movie movie1 = new Movie("Batmand", "Sej film", 10, "Action", "2 timer", "pg 13", 100, LocalDate.of(2022, 9,1), LocalDate.of(2022, 12,1));
        Movie movie2 = new Movie("Hello kitty", "sød film", 2, "Romance", "1 time", "pg 7", 50, LocalDate.of(2023, 1,1), LocalDate.of(2023, 3,1));
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Theater theater1 = new Theater(1);
        Theater theater2 = new Theater(2);
        theaterRepository.save(theater1);
        theaterRepository.save(theater2);

        Screening screening1 = new Screening(10, LocalDateTime.of(2022,9,9,10,10), LocalDateTime.of(2022,9,9,11,50), movie1, theater1);
        Screening screening2 = new Screening(10, LocalDateTime.of(2022,9,9,10,10), LocalDateTime.of(2022,9,9,11,50), movie2,  theater2);
        Screening screening3 = new Screening(120, LocalDateTime.of(2022,9,9,10,10), LocalDateTime.of(2022,9,9,11,50), movie2,  theater2);
        screeningRepository.save(screening1);
        screeningRepository.save(screening2);

        sc1 = screening1;
        sc2 = screening2;
        sc3 = screening3;

        screeningService = new ScreeningService(screeningRepository, movieRepository, theaterRepository);


    }


    @Test
    void getAllScreenings() {
        List<ScreeningResponse> screenings = screeningService.getAllScreenings();
        assertEquals(2, screenings.size());
        assertNotEquals(3, screenings.size());
    }

    @Test
    void getScreeningById() {
        ScreeningResponse screeningResponse = screeningService.getScreeningById(2);
        assertEquals(screeningResponse.getId(), sc2.getId());
        assertNotEquals(screeningResponse.getId(), sc1.getId());
    }

    @Test
    void addScreening() {
        ScreeningRequest screeningRequest = new ScreeningRequest(sc3);
        screeningService.addScreening(screeningRequest);
        List<ScreeningResponse> screeningResponses = screeningService.getAllScreenings();
        assertEquals(3, screeningResponses.size());
    }

    @Test
    void editScreening() {
    }

    @Test
    void deleteScreening() {
    }
}