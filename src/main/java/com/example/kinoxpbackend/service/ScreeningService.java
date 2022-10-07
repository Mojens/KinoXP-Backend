package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.EmployeeResponse;
import com.example.kinoxpbackend.dto.MovieRequest;
import com.example.kinoxpbackend.dto.ScreeningRequest;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.entity.*;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ReservationRepository;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ReservationRepository reservationRepository;

    public ScreeningService(ScreeningRepository screeningRepository, MovieRepository movieRepository, TheaterRepository theaterRepository, ReservationRepository reservationRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.reservationRepository = reservationRepository;
    }

    // GetAll screenings
    public List<ScreeningResponse> getAllScreenings() {
        List<Screening> screeningList = screeningRepository.findAll();
        if (screeningList.size() < 1) {
            System.out.println("No list");
        } else if (screeningList.size() > 1) {
            System.out.println("There is a List");
            for (Screening s : screeningList
            ) {
                System.out.println(s);
            }
        }
        // sort by date

        return screeningList.stream().map(screening -> new ScreeningResponse(screening)).toList();
    }

    // get screening by id
    public ScreeningResponse getScreeningById(@PathVariable int id) {
        return new ScreeningResponse(screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screening not found")));
    }

    // add screening
    public ScreeningResponse addScreening(ScreeningRequest screeningRequest) {
        if (screeningRepository.existsScreeningById(screeningRequest.getId())) {
            throw new RuntimeException("Screening with this ID already exist");
        }
        Screening newScreening = ScreeningRequest.getScreeningEntity(screeningRequest);
        newScreening = screeningRepository.save(newScreening);


        screeningRepository.save(newScreening);


        return new ScreeningResponse(newScreening);
    }

    // edit screening
    public void editScreening(ScreeningRequest screeningRequest, int id) {
        Screening screening = screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screening not found"));

        screening.setPerformance(screeningRequest.getPerformance());
        screening.setStartTime(screeningRequest.getStartTime());
        screening.setEndTime(screeningRequest.getEndTime());

        screeningRepository.save(screening);
    }

    // delete screening
    public void deleteScreening(@PathVariable int id) {
        if (!screeningRepository.existsScreeningById(id)) {
            throw new RuntimeException("Screening not found");
        }
        screeningRepository.deleteById(id);
    }

    // add all screenings to movie between two dates
    public ScreeningResponse addScreeningsToMovie(ScreeningRequest screeningRequest) {
        if (screeningRepository.existsScreeningById(screeningRequest.getId())) {
            throw new RuntimeException("Screening with this ID already exist");
        }

        Screening newScreening = ScreeningRequest.getScreeningEntity(screeningRequest);
        // get movie showStart and showEnd
        Movie movie = movieRepository.findById(screeningRequest.getMovieId()).orElseThrow(() -> new RuntimeException("Movie not found"));
        LocalDate showStart = movie.getShowStartDate();
        LocalDate showEnd = movie.getShowEndDate();
        // difference between showStart and showEnd
        int days = showEnd.getDayOfYear() - showStart.getDayOfYear();

        for (int i = 0; i < days; i++) {

            newScreening.setStartTime(newScreening.getStartTime().plusDays(i));
            newScreening.setEndTime(newScreening.getEndTime().plusDays(i));
            screeningRepository.save(newScreening);
            newScreening = ScreeningRequest.getScreeningEntity(screeningRequest);

        }

        return new ScreeningResponse(newScreening);
    }

    // find all booked seats for a specific screening and return number of booked seats
    public int getNumberOfReservations(int screeningId) {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> reservationsForScreening = new ArrayList<>();
        List<SeatChoice> seatChoices = new ArrayList<>();

        for (Reservation r : reservations
        ) {
            if (r.getScreening().getId() == screeningId) {
                reservationsForScreening.add(r);
            }
        }
        for (Reservation r : reservationsForScreening
        ) {
            seatChoices.addAll(r.getSeatChoices());

        }
        return seatChoices.size();


    }


    public void updatePerformance(int id) {
        Screening screening = screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screening not found"));
        int seatsBooked = getNumberOfReservations(id);
        Theater theater = theaterRepository.findById(screening.getTheater().getId()).orElseThrow(() -> new RuntimeException("Theater not found"));

        int performance;
        if (theater.getId() == 1) {
            performance = (seatsBooked * 100) / 240;

        } else {
            performance = (seatsBooked * 100) / 400;

        }
        screening.setPerformance(performance);
        screeningRepository.save(screening);

    }




    public void updateAllPerformance() {
        List<Screening> screenings = screeningRepository.findAll();
        for (Screening s : screenings
        ) {
            updatePerformance(s.getId());
        }

    }

    // get average performance for a specific movie last 7 days
    public int getWeeklyPerformance(int movieId) {
        List<Screening> screenings = screeningRepository.findAll();
        List<Screening> screeningsForMovie = new ArrayList<>();
        List<Double> performances = new ArrayList<>();
        for (Screening s : screenings
        ) {
            if (s.getMovie().getId() == movieId) {
                screeningsForMovie.add(s);
            }
        }
        for (Screening s : screeningsForMovie
        ) {
            if (s.getStartTime().isAfter(LocalDateTime.now().minusDays(7))) {
                performances.add(s.getPerformance());
            }
        }
        int sum = 0;
        for (double i : performances
        ) {
            sum += i;
        }
        return sum / performances.size();
    }

    // set weeklyPerformance for a specific movie
    public void setWeeklyPerformance(int movieId) {
        Screening screening = screeningRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        screening.setWeeklyPerformance(getWeeklyPerformance(movieId));
        movieRepository.save(screening.getMovie());
    }

}
