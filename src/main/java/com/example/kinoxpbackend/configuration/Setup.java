package com.example.kinoxpbackend.configuration;


import com.example.kinoxpbackend.entity.*;
import com.example.kinoxpbackend.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class Setup implements ApplicationRunner {

  MovieRepository movieRepository;
  ScreeningRepository screeningRepository;
  TheaterRepository theaterRepository;
  SeatingRepository seatRepository;
  EmployeeRepository employeeRepository;
  ShiftRepository shiftRepository;
  ReservationRepository reservationRepository;
  SeatChoiceRepository seatChoiceRepository;

  public Setup(MovieRepository movieRepository,
               ScreeningRepository screeningRepository,
               TheaterRepository theaterRepository,
               SeatingRepository seatRepository,
               ShiftRepository shiftRepository,
               EmployeeRepository employeeRepository,
               ReservationRepository reservationRepository,
               SeatChoiceRepository seatChoiceRepository) {
    this.movieRepository = movieRepository;
    this.screeningRepository = screeningRepository;
    this.theaterRepository = theaterRepository;
    this.seatRepository = seatRepository;
    this.shiftRepository = shiftRepository;
    this.employeeRepository = employeeRepository;
    this.reservationRepository = reservationRepository;
    this.seatChoiceRepository = seatChoiceRepository;
  }


    @Override
    public void run (ApplicationArguments args) throws Exception {

      // Add movie
      Movie movie = new Movie("Batman Begins", "Good movie", 5, "Action", 150, 15, 100, LocalDate.of(2022, 10, 10), LocalDate.of(2022, 12, 26));
      Movie movie2 = new Movie("Batman Dark knight", "Best movie", 5, "Action", 150, 15, 100, LocalDate.of(2022, 10, 10), LocalDate.of(2022, 12, 26));
      Movie movie3 = new Movie("Batman Rises", "Decent movie", 5, "Action", 150, 15, 100, LocalDate.of(2022, 10, 10), LocalDate.of(2022, 12, 26));
      Movie movie4 = new Movie("The Batman", "Weird movie", 5, "Action", 150, 15, 100, LocalDate.of(2022, 10, 10), LocalDate.of(2022, 12, 26));

      movieRepository.save(movie);
      movieRepository.save(movie2);
      movieRepository.save(movie3);
      movieRepository.save(movie4);


      // add Theater
      Theater theater = new Theater(1);
      Theater theater2 = new Theater(2);


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


      for (int i = 0; i < 25; i++) {
        String row = rows[i];
        for (int j = 1; j <= 16; j++) {
          Seatings tempSeat = new Seatings(row, j, theater2);
          seatsTheater1.add(tempSeat);
        }
      }
      seatRepository.saveAll(seatsTheater1);


    // add screenings
    Screening screening = new Screening(10, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,10,23,10),movie2, theater);
    Screening screening2 = new Screening(40, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,11,23,10),movie2, theater2);
    Screening screening3 = new Screening(30, LocalDateTime.of(2022,10,10,20,10),LocalDateTime.of(2022,10,11,23,10),movie2, theater);


    screeningRepository.save(screening);
    screeningRepository.save(screening2);
    screeningRepository.save(screening3);


      Employee employee1 = Employee.builder()
          .name("Jens")
          .type(4)
          .password("test123")
          .userName("JensAdmin")
          .build();
      Employee employee3 = Employee.builder()
          .name("Simon")
          .type(4)
          .password("test123")
          .userName("SimonOlsen")
          .build();

      Employee employee2 = Employee.builder()
          .name("Mo")
          .type(1)
          .password("test321")
          .userName("MoCasual")
          .build();

      Shift shift1 = Shift.builder()
          .startTime(LocalDateTime.now())
          .endTime(LocalDateTime.now().plusHours(8))
          .employee(employee1)
          .build();

      Shift shift2 = Shift.builder()
          .startTime(LocalDateTime.now().plusHours(8))
          .endTime(LocalDateTime.now().plusHours(16))
          .employee(employee2)
          .build();

      employeeRepository.save(employee1);
      employeeRepository.save(employee2);
      employeeRepository.save(employee3);

      shiftRepository.save(shift1);
      shiftRepository.save(shift2);

      Reservation reservation1 = new Reservation("Jan", "61426729", 1, "h", screeningRepository.findScreeningById(1));
      Reservation reservation2 = new Reservation("Bo", "61426786", 2, "ui", screeningRepository.findScreeningById(2));
      Reservation reservation3 = new Reservation("Lars", "61426700", 3, "a", screeningRepository.findScreeningById(3));
      reservationRepository.save(reservation1);
      reservationRepository.save(reservation2);
      reservationRepository.save(reservation3);

      SeatChoice seatChoice1 = SeatChoice.builder()
              .seatings(seatRepository.getSeatingsById(8))
              .reservation(reservationRepository.getReservationById(1))
              .build();

      SeatChoice seatChoice2 = SeatChoice.builder()
              .seatings(seatRepository.getSeatingsById(10))
              .reservation(reservationRepository.getReservationById(2))
              .build();

      SeatChoice seatChoice3 = SeatChoice.builder()
              .seatings(seatRepository.getSeatingsById(1))
              .reservation(reservationRepository.getReservationById(3))
              .build();

      SeatChoice seatChoice4 = SeatChoice.builder()
              .seatings(seatRepository.getSeatingsById(2))
              .reservation(reservationRepository.getReservationById(3))
              .build();

      seatChoiceRepository.save(seatChoice1);
      seatChoiceRepository.save(seatChoice2);
      seatChoiceRepository.save(seatChoice3);
      seatChoiceRepository.save(seatChoice4);

    }

}
