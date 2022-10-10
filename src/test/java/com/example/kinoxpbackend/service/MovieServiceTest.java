package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.repository.MovieRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MovieServiceTest {

    public static MovieRepository movieRepository;


    @BeforeAll
    public static void setUp(@Autowired MovieRepository movie_repository){

        movieRepository = movie_repository;

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

        Movie movie2 = Movie.builder().title("Peter plys & Skipper skræk")
                .description("Peter plys møder på en sejltur den berygtede Skipper Skræk. Der er skumle miner i luften men mon ikke de nok skal blive venner i sidste ende.")
                .rating(6.3)
                .genre("Drama, Børn, Comedy")
                .duration("1h 19min")
                .ageLimit("PG-7")
                .price(90)
                    .photo("https://static.posters.cz/image/1300/plakater/winnie-the-pooh-i10378.jpg")
                .stars("Winnie the Pooh, Tigerdyr")
                .trailers("https://www.youtube.com/watch?v=W3E74j_xFtg&ab_channel=RottenTomatoesTrailers")
                .showStartDate(LocalDate.of(2022, 10, 15))
                .showEndDate(LocalDate.of(2023, 1, 15))
                .screenings(new ArrayList<>())
                .build();

        Movie movie3 = Movie.builder().title("James Bond")
                .description("James bond saves the queen of England once again.")
                .rating(9.2)
                .genre("Action, Crime, Drama, Comedy")
                .duration("2h 30min")
                .ageLimit("PG-15")
                .price(120)
                .photo("https://www.kino.dk/sites/default/files/styles/k_width_big/public/primary-pictures/bond-kavalkade-2021-thumb_0.jpg?itok=xqD4fJXp")
                .stars("Daniel Craig, Sean Connery, Roger Moore, Pierce Brosnan, Timothy Dalton")
                .trailers("https://www.youtube.com/watch?v=BIhNsAtPbPI&ab_channel=JamesBond007")
                .showStartDate(LocalDate.of(2022, 10, 11))
                .showEndDate(LocalDate.of(2022, 1, 1))
                .screenings(new ArrayList<>())
                .build();

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

        movieRepository.saveAll(movies);
    }

    @Test
    void getAllMovies() {
        //actual result
        int actualResult = movieRepository.findAll().size();

        //Excpected result
        int expectedResult = 3;

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void addMovie() {
        String expectedDescription = "";
        Movie newMovie = Movie.builder().title("Harry potter og de vises sten")
                .description(expectedDescription)
                .rating(9.2)
                .genre("Action, Drama, teenage")
                .duration("2h 10min")
                .ageLimit("PG-11")
                .price(140)
                .photo("https://imgcdn.saxo.com/_9788702173222")
                .stars("Daniel Radcliff, Hermione Granger, Ron Weasley")
                .trailers("https://www.youtube.com/watch?v=BIhNsAtPbPI&ab_channel=JamesBond007")
                .showStartDate(LocalDate.of(2022, 10, 11))
                .showEndDate(LocalDate.of(2022, 1, 1))
                .screenings(new ArrayList<>())
                .build();

        Movie addedMovie = movieRepository.save(newMovie);

        String actualDescription = addedMovie.getDescription();



    }

    @Test
    void deleteMovie() {
    }
}