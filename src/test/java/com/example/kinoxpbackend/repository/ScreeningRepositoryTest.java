package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Theater;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScreeningRepositoryTest {

    static ScreeningRepository screeningRepository;


    static MovieRepository movieRepository;


    static TheaterRepository theaterRepository;

    static Screening sc1;
    static Screening sc2;





    @BeforeAll
    public static void setupData(@Autowired ScreeningRepository screening_Repository, @Autowired MovieRepository movie_Repository, @Autowired TheaterRepository theater_Repository ) {
        screeningRepository = screening_Repository;
        movieRepository =  movie_Repository;
        theaterRepository = theater_Repository;

        Movie movie1 = new Movie(1,"Batmand", "Sej film", 10, "Action", "2 timer", "pg 13", 100, LocalDate.of(2022, 9,1), LocalDate.of(2022, 12,1));
        Movie movie2 = new Movie(2,"Hello kitty", "sød film", 2, "Romance", "1 time", "pg 7", 50, LocalDate.of(2023, 1,1), LocalDate.of(2023, 3,1));
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Theater theater1 = new Theater(1);
        Theater theater2 = new Theater(2);
        theaterRepository.save(theater1);
        theaterRepository.save(theater2);

        Screening screening1 = new Screening(1,10, LocalDateTime.of(2022,9,9,10,10), LocalDateTime.of(2022,9,9,11,50), movie1, theater1);
        Screening screening2 = new Screening(2,10, LocalDateTime.of(2022,9,9,10,10), LocalDateTime.of(2022,9,9,11,50), movie2,  theater2);
        screeningRepository.save(screening1);
        screeningRepository.save(screening2);

        sc1 = screening1;
        sc2 = screening2;

        if (screeningRepository.findAll().size() > 2) {
            screeningRepository.deleteById(3);
            screeningRepository.deleteById(4);
        }

    }


    @Test
    void existsScreeningById() {
        assertTrue(screeningRepository.existsScreeningById(1));
        assertFalse(screeningRepository.existsScreeningById(3));

    }

    @Test
    void findScreeningById() {
        Screening foundScreening = screeningRepository.findScreeningById(1);
        assertEquals(foundScreening.getId(), sc1.getId());
        assertNotEquals(foundScreening.getId(), sc2.getId());
    }
}