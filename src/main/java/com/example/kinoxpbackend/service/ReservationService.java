package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.ReservationRequest;
import com.example.kinoxpbackend.dto.ReservationResponse;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    public ReservationResponse addReservation(ReservationRequest body) {
        return null;
    }

    public void editReservation(ReservationRequest body, int id) {
    }

    public List<ReservationResponse> findReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> response = reservations.stream().map(reservation -> new ReservationResponse(reservation)).collect(Collectors.toList());
        return response;
    }

    public ReservationResponse findReservationById(int id) {
        return null;
    }

    public void deleteReservation(int id) {
    }
}
