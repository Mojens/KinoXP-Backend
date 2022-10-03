package com.example.kinoxpbackend.configuration;


import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

@Controller
public class Setup implements ApplicationRunner {

  MovieRepository movieRepository;
  ScreeningRepository screeningRepository;
  TheaterRepository theaterRepository;

  public Setup(MovieRepository movieRepository, ScreeningRepository screeningRepository, TheaterRepository theaterRepository) {
    this.movieRepository = movieRepository;
    this.screeningRepository = screeningRepository;
    this.theaterRepository = theaterRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

      // Add movie
    Movie movie = new Movie("Batman Begins","Best movie",5, "Action",150,15,100,LocalDate.of(2022,10,10),LocalDate.of(2022,12,26));
    Movie movie2 = new Movie("Batman Dark knight","Best movie",5, "Action",150,15,100,LocalDate.of(2022,10,10),LocalDate.of(2022,12,26));
    Movie movie3 = new Movie("Batman Rises","Best movie",5, "Action",150,15,100,LocalDate.of(2022,10,10),LocalDate.of(2022,12,26));
    Movie movie4 = new Movie("The Batman","Best movie",5, "Action",150,15,100,LocalDate.of(2022,10,10),LocalDate.of(2022,12,26));

    movieRepository.save(movie);
    movieRepository.save(movie2);
    movieRepository.save(movie3);
    movieRepository.save(movie4);


    // add Theater
    Theater theater = new Theater( 1);
    Theater theater2 = new Theater( 2);

    theaterRepository.save(theater);
    theaterRepository.save(theater2);

    // add screenings
    Screening screening = new Screening(10, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,10,23,10),movie2, theater);
    Screening screening2 = new Screening(40, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,11,23,10),movie2, theater2);
    Screening screening3 = new Screening(30, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,11,23,10),movie2, theater);


    screeningRepository.save(screening);
    screeningRepository.save(screening2);
    screeningRepository.save(screening3);


  }
}
