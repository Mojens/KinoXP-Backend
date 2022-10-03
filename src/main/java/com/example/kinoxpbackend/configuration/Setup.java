package com.example.kinoxpbackend.configuration;


import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Seatings;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import com.example.kinoxpbackend.repository.SeatRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class Setup implements ApplicationRunner {

  MovieRepository movieRepository;
  ScreeningRepository screeningRepository;
  TheaterRepository theaterRepository;
  SeatRepository seatRepository;

  public Setup(MovieRepository movieRepository, ScreeningRepository screeningRepository, TheaterRepository theaterRepository, SeatRepository seatRepository) {
    this.movieRepository = movieRepository;
    this.screeningRepository = screeningRepository;
    this.theaterRepository = theaterRepository;
    this.seatRepository = seatRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

      // Add movie
    Movie movie = new Movie("Batman Begins","Best movie",5, "Action",150,15,100, LocalDate.of(2022,10,10),LocalDate.of(2022,12,26));
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

    // Seats


    List<Seatings> seatsTheater1 = new ArrayList<Seatings>();
    String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
    for (int i = 0; i < 20; i++) {
      String row = rows[i];
      for (int j = 1; j <= 12; j++) {
        Seatings tempSeat = new Seatings(row, j, theater);
        seatsTheater1.add(tempSeat);
      }
    }

    List<Seatings> seatsTheater2 = new ArrayList<Seatings>();
    for (int i = 0; i < 25; i++) {
      String row = rows[i];
      for (int j = 1; j <= 16; j++) {
        Seatings tempSeat = new Seatings(row, j, theater2);
        seatsTheater1.add(tempSeat);
      }
    }
    seatRepository.saveAll(seatsTheater1);

/*
    // add screenings
    Screening screening = new Screening(10, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,10,23,10),movie2, theater);
    Screening screening2 = new Screening(40, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,11,23,10),movie2, theater2);
    Screening screening3 = new Screening(30, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,11,23,10),movie2, theater);


    screeningRepository.save(screening);
    screeningRepository.save(screening2);
    screeningRepository.save(screening3);
*/

  }

  public static void seatConfig(){




  }

  /*
    @Override
    public void run(ApplicationArguments args){
        ArrayList<Reservation> reservations = new ArrayList<Reservation>(
                Arrays.asList(
                        new Reservation("nfnfjd@lddjk.dk","30208430", 1, "123456789"),
                        new Reservation("nfcdefjd@lddjk.dk","30205430", 5, "12345dew6789"),
                        new Reservation("frefwe@lddjk.dk","30328430", 4, "dfwedwed")
                )
        );
        reservationRepository.saveAll(reservations);
    }
    */

}
