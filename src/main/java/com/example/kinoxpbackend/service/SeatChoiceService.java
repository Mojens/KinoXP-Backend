package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.*;
import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.SeatChoice;
import com.example.kinoxpbackend.repository.ReservationRepository;
import com.example.kinoxpbackend.repository.SeatChoiceRepository;
import com.example.kinoxpbackend.repository.SeatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatChoiceService {
    private final SeatChoiceRepository seatChoiceRepository;

    private final SeatingRepository seatingRepository;

    private final ReservationRepository reservationRepository;

    public SeatChoiceService(SeatChoiceRepository seatChoiceRepository, SeatingRepository seatingRepository, ReservationRepository reservationRepository) {
        this.seatChoiceRepository = seatChoiceRepository;
        this.seatingRepository = seatingRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<SeatChoiceResponse> getAllSeatChoices() {
        return seatChoiceRepository.findAll().stream().map(SeatChoiceResponse::new).collect(Collectors.toList());
    }

    public SeatChoiceResponse getSeatChoiceById(@PathVariable int id) {
        return new SeatChoiceResponse(seatChoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("SeatChoice not found")));
    }

    public SeatChoiceResponse addSeatChoice(SeatChoiceRequest seatChoiceRequest) {
        if (seatChoiceRepository.existsById(seatChoiceRequest.getId())) {
            throw new RuntimeException("SeatChoice with this ID already exist");
        }
        SeatChoice createdSeatChoice = SeatChoice.builder()
                .seatings(seatingRepository.getSeatingsById(seatChoiceRequest.getSeatingsId()))
                .reservation(reservationRepository.getReservationById(seatChoiceRequest.getSeatingsId()))
                .build();

        //Screening newScreening = ScreeningRequest.getScreeningEntity(screeningRequest);
        seatChoiceRepository.save(createdSeatChoice);

        return new SeatChoiceResponse(createdSeatChoice);
    }

    public void editSeatChoice(SeatChoiceRequest seatChoiceRequest, int id) {
        SeatChoice seatChoice = seatChoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("SeatChoice with this ID does not exist"));
        if (seatChoiceRequest.getId() != id) {
            throw new RuntimeException("Cannot change ID");
        }

        seatChoice.setSeatings(seatingRepository.getSeatingsById(seatChoiceRequest.getSeatingsId()));
        seatChoice.setReservation(reservationRepository.getReservationById(seatChoiceRequest.getReservationId()));
        seatChoiceRepository.save(seatChoice);
    }

    public void deleteSeatChoice(@PathVariable int id) {
        SeatChoice seatChoice = seatChoiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"SeatChoice with this ID does not exist"));
        seatChoiceRepository.delete(seatChoice);
    }

}
