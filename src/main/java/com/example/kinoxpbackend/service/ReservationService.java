package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.*;
import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.repository.ReservationRepository;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    ScreeningRepository screeningRepository;

    public ReservationService(ReservationRepository reservationRepository, ScreeningRepository screeningRepository){
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
    }



    public List<ReservationResponse> findReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> response = reservations.stream().map(reservation -> new ReservationResponse(reservation)).collect(Collectors.toList());
        return response;
    }

    public ReservationResponse findReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        return new ReservationResponse(reservation);
    }



    public void deleteReservation(int id) {
        if(!reservationRepository.existsById(id)){
            throw new RuntimeException("Reservation not found");
        }
        System.out.println("Before delete");
        reservationRepository.deleteById(id);
        System.out.println("After delete");

    }


    public ReservationResponse addReservation(ReservationRequest body) {
        String safetyId = getSafetyId(16);

        Screening screening = screeningRepository.findScreeningById(body.getScreeningId());

        Reservation reservation = Reservation.builder()
                .email(body.getEmail())
                .phoneNumber(body.getPhoneNumber())
                .employeeId(body.getEmployeeId())
                .safetyId(safetyId)
                .screening(screening).build();
        reservationRepository.save(reservation);
        return new ReservationResponse(reservation);
    }

    public void editReservation(ReservationRequest body, int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setEmail(body.getEmail());
        reservation.setPhoneNumber(body.getPhoneNumber());
        reservation.setEmployeeId(body.getEmployeeId());
        reservationRepository.save(reservation);

    }


    private String getSafetyId(int lenght){
        Random random = new Random();
        String safetyId;
        do {
            safetyId = "";
            for (int i = 0; i < lenght; i++) {
                safetyId += random.nextInt(10);
            }
        }while(reservationRepository.existsBySafetyId(safetyId));

        return safetyId;
    }

}
