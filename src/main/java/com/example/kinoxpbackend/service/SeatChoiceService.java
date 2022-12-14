package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.*;
import com.example.kinoxpbackend.entity.SeatChoice;
import com.example.kinoxpbackend.repository.ReservationRepository;
import com.example.kinoxpbackend.repository.SeatChoiceRepository;
import com.example.kinoxpbackend.repository.SeatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
                .seatings(seatingRepository.getSeatingById(seatChoiceRequest.getSeatingsId()))
                .reservation(reservationRepository.getReservationById(seatChoiceRequest.getReservationId()))
                .build();

        //Screening newScreening = ScreeningRequest.getScreeningEntity(screeningRequest);
        seatChoiceRepository.save(createdSeatChoice);

        return new SeatChoiceResponse(createdSeatChoice);
    }

    public void editSeatChoice(SeatChoiceRequest seatChoiceRequest, int id) {
        SeatChoice seatChoice = seatChoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("SeatChoice with this ID does not exist"));

        seatChoice.setSeatings(seatingRepository.getSeatingById(seatChoiceRequest.getSeatingsId()));
        seatChoice.setReservation(reservationRepository.getReservationById(seatChoiceRequest.getReservationId()));
        seatChoiceRepository.save(seatChoice);
    }

    public void deleteSeatChoice(@PathVariable int id) {
        SeatChoice seatChoice = seatChoiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"SeatChoice with this ID does not exist"));
        seatChoiceRepository.delete(seatChoice);
    }

    public List<SeatChoiceResponse> getSeatChoiceByReservationId(int id) {
        return seatChoiceRepository.getSeatChoicesByReservationId(id);
    }

     public List<SeatChoiceResponse> addListChoiceList(List<SeatChoiceRequest> seatChoiceRequest) {
        List<SeatChoiceResponse> seatChoiceResponses = new ArrayList<>();
         System.out.println(seatChoiceRequest);
        for (SeatChoiceRequest e : seatChoiceRequest){
            SeatChoice createdSeatChoice = SeatChoice.builder()
                    .seatings(seatingRepository.getSeatingById(e.getSeatingsId()))
                    .reservation(reservationRepository.getReservationById(e.getReservationId()))
                    .build();
            seatChoiceRepository.save(createdSeatChoice);
            seatChoiceResponses.add(new SeatChoiceResponse(createdSeatChoice));
        }
        return seatChoiceResponses;
    }
}
