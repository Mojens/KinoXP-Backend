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
    public void run(ApplicationArguments args) throws Exception {


        Movie movie = Movie.builder().title("Batman Begins")
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

        // COpy movie2 as a build
        Movie movie2 = Movie.builder().title("The Dark knight")
                .description("When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.")
                .rating(9)
                .genre("Action, Crime, Drama")
                .duration("2h 12min")
                .ageLimit("PG-13")
                .price(100)
                .photo("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg")
                .stars("Christian Bale, Heath Ledger, Aaron Eckhart")
                .trailers("https://www.imdb.com/video/vi324468761/")
                .showStartDate(LocalDate.of(2022, 10, 10))
                .showEndDate(LocalDate.of(2022, 12, 26))
                .screenings(new ArrayList<>())
                .build();

        // Copy movie3 as a build
        Movie movie3 = Movie.builder().title("The Dark Knight Rises")
                .description("Eight years after the Joker's reign of anarchy, Batman, with the help of the enigmatic Selina Kyle, is forced from his exile to save Gotham City from the brutal guerrilla terrorist Bane.")
                .rating(8.4)
                .genre("Action, Drama")
                .duration("2h 44min")
                .ageLimit("PG-13")
                .price(100)
                .photo("https://m.media-amazon.com/images/M/MV5BMTk4ODQzNDY3Ml5BMl5BanBnXkFtZTcwODA0NTM4Nw@@._V1_.jpg")
                .stars("Christian Bale, Tom Hardy, Anne Hathaway")
                .trailers("https://www.imdb.com/video/vi144884505/")
                .showStartDate(LocalDate.of(2022,10,22))
                .showEndDate(LocalDate.of(2022,12,22))
                .screenings(new ArrayList<>())
                .build();


        Movie movie4 = Movie.builder().title("The Batman")

                .description("When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.")
                .rating(7.9)
                .genre("Action, Drama, Crime")
                .duration("2h 56min")
                .ageLimit("PG-13")
                .price(100)
                .photo("https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_FMjpg_UX1000_.jpg")
                .stars("Robert Pattinson, Zoë Kravitz, Jeffery Wright")
                .trailers("https://www.imdb.com/video/vi3215114777/")
                .showStartDate(LocalDate.of(2022, 10, 10))
                .showEndDate(LocalDate.of(2022, 12, 26))
                .screenings(new ArrayList<>())
                .build();



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
        Screening screening = new Screening(10, LocalDateTime.of(2022, 10, 10, 20, 25), LocalDateTime.of(2022, 10, 10, 23, 55), movie2, theater);
        Screening screening2 = new Screening(40, LocalDateTime.of(2022, 10, 11, 20, 20), LocalDateTime.of(2022, 11, 11, 23, 10), movie, theater2);
        Screening screening3 = new Screening(30, LocalDateTime.of(2022, 10, 12, 20, 15), LocalDateTime.of(2022, 12, 11, 23, 1), movie2, theater);


        screeningRepository.save(screening);
        screeningRepository.save(screening2);
        screeningRepository.save(screening3);




        Employee employee1 = Employee.builder()
                .name("Jens")
                .type(1)
                .password("test123")
                .userName("JensAdmin")
                .build();

        Employee employee3 = Employee.builder()
                .name("Simon")
                .type(1)
                .password("test123")
                .userName("SimonOlsen")
                .build();

        Employee employee2 = Employee.builder()
                .name("Mo")
                .type(2)
                .password("test321")
                .userName("MoCasual")
                .build();

      Shift shift1 = Shift.builder()
          .startTime(LocalDateTime.of(2022,10,10,9,0))
          .endTime(LocalDateTime.of(2022,10,10,17,0))
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
                .seatings(seatRepository.getSeatingById(8))
                .reservation(reservationRepository.getReservationById(1))
                .build();

        SeatChoice seatChoice2 = SeatChoice.builder()
                .seatings(seatRepository.getSeatingById(10))
                .reservation(reservationRepository.getReservationById(1))
                .build();

        SeatChoice seatChoice3 = SeatChoice.builder()
                .seatings(seatRepository.getSeatingById(1))
                .reservation(reservationRepository.getReservationById(1))
                .build();

        SeatChoice seatChoice4 = SeatChoice.builder()
                .seatings(seatRepository.getSeatingById(2))
                .reservation(reservationRepository.getReservationById(1))
                .build();

        seatChoiceRepository.save(seatChoice1);
        seatChoiceRepository.save(seatChoice2);
        seatChoiceRepository.save(seatChoice3);
        seatChoiceRepository.save(seatChoice4);

    }

}
