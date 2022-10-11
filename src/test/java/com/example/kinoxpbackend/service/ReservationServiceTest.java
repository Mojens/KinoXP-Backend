package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.ReservationResponse;
import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.*;
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
class ReservationServiceTest {

//Testing2
    public static ReservationRepository reservationRepository;

    public static TheaterRepository theaterRepository;

    public static MovieRepository movieRepository;

    public static ScreeningRepository screeningRepository;


    SeatChoiceService seatChoiceService;

    public static SeatingRepository seatingRepository;


    @BeforeAll
    static void  makeData(
            @Autowired ReservationRepository reservation_Repository,
            @Autowired TheaterRepository theater_Repository,
            @Autowired MovieRepository movie_Repository,
            @Autowired ScreeningRepository screening_Repository,
            @Autowired SeatingRepository seating_Repository){

        reservationRepository = reservation_Repository;
        theaterRepository = theater_Repository;
        movieRepository = movie_Repository;
        screeningRepository = screening_Repository;
        seatingRepository = seating_Repository;
        //Creating a theater
        Theater theater1 = new Theater(1);
        theaterRepository.save(theater1);

        //Creating a movie
        Movie movie1 = Movie.builder().title("Batman Begins")
                .description("After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from corruption.")
                .rating(8.2)
                .genre("Action, Crime, Drama")
                .duration("2h 20min")
                .ageLimit("PG-13")
                .price(100)
                .photo("https://m.media-amazon.com/images/M/MV5BOTY4YjI2N2MtYmFlMC00ZjcyLTg3YjEtMDQyM2ZjYzQ5YWFkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg")
                .stars("Christian Bale, Michael Caine, Ken Watanabe")
                .trailers("https://www.imdb.com/video/vi362988313/")
                .showStartDate(LocalDate.of(2022, 10, 10))
                .showEndDate(LocalDate.of(2022, 12, 26))
                .screenings(new ArrayList<>())
                .build();

        movieRepository.save(movie1);

        //Creating a screening
        Screening screening = new Screening(10, LocalDateTime.of(2022, 10, 10, 20, 25), LocalDateTime.of(2022, 10, 10, 23, 55), movie1, theater1);
        screeningRepository.save(screening);

        //Creating a few Reservations
        ArrayList<Reservation> reservations = new ArrayList<>(
                Arrays.asList(
                        new Reservation("jan", "61426729", 1, "h", screening),
                        new Reservation("Bo", "61426786", 2, "ui", screening),
                         new Reservation("Lars", "61426700", 1, "a", screening)
                )
        );
        reservationRepository.saveAll(reservations);

    }





    @Test
    void addReservation() {
        String email = "tester@test.com";
        Screening screening = screeningRepository.findScreeningById(1);
        Reservation reservation = new Reservation(email, "11291230", 3, "1", screening);
        reservationRepository.save(reservation);
        //List<Reservation> reservations = reservationRepository.findAll();

        assertEquals(email, reservationRepository.getReservationById(4).getEmail());
    }
    @Test
    void findReservations() {
        //result
        int reservationAmount = reservationRepository.findAll().size();

        //Expected result
        int expectedResult = 3;


        //Asserting
        assertEquals(reservationAmount, expectedResult);

    }

    @Test
    void trueTest(){
        String hej = "Hello";
        assertEquals("Hello", hej);
    }


    @Test
    void getSafetyId() {
        int lengthOfSafetyId = 16;
        ReservationService service = new ReservationService(reservationRepository, screeningRepository, seatChoiceService, seatingRepository);

        //Result
        String safetyId1 = service.getSafetyId(lengthOfSafetyId);
        String safetyId2 = service.getSafetyId(lengthOfSafetyId);

        boolean isSame = safetyId1.equals(safetyId2);


        //Expected Result
        boolean isSameResult = false;

        //Here we check if both safetyId's is the lenght of 16
        assertEquals(safetyId1.length(), lengthOfSafetyId);
        assertEquals(safetyId2.length(), lengthOfSafetyId);


        //Here we check if the safetyID's is the same. They should not be.
        assertEquals(isSame, isSameResult);
    }

    @Test
    void deleteReservation() {
        List<Reservation> reservationsBeforeDeleting = reservationRepository.findAll();
        reservationRepository.deleteById(1);
        List<Reservation> reservationsAfterDeleting = reservationRepository.findAll();



        //result
        int sizeBefore = reservationsBeforeDeleting.size();
        int sizeAfter = reservationsAfterDeleting.size();

        //Expected result
        int expectedBefore = 3;
        int expectedAfter = 2;


        //asserting
        assertEquals(sizeBefore, expectedBefore);
        assertEquals(sizeAfter, expectedAfter);
    }


}