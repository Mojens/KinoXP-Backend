package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.*;
import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;



    public List<ReservationResponse> findReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> response = reservations.stream().map(reservation -> new ReservationResponse(reservation)).collect(Collectors.toList());
        return response;
    }

    public ReservationResponse findReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        return new ReservationResponse(reservation);
    }



    public void deleteReservation(@PathVariable int id) {
        if(!reservationRepository.existsById(id)){
            throw new RuntimeException("Reservaation not found");
        }
    }


    public ReservationResponse addReservation(ReservationRequest body) {

        if(reservationRepository.existsById(body.getId())){
            throw new RuntimeException("Reservation with this ID allready exist");
        }
        Reservation reservation = ReservationRequest.getReservationEntity(body);
        reservationRepository.save(reservation);
        return new ReservationResponse(reservation);
    }

    public void editReservation(ReservationRequest body, int id) {
    }
}
