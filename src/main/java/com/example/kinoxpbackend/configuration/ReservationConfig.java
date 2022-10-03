package com.example.kinoxpbackend.configuration;

import com.example.kinoxpbackend.dto.ReservationResponse;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.repository.ReservationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class ReservationConfig implements ApplicationRunner {



    ReservationRepository reservationRepository;


    public ReservationConfig(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

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
}
