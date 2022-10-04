package com.example.kinoxpbackend.api;


import com.example.kinoxpbackend.dto.ReservationRequest;
import com.example.kinoxpbackend.dto.ReservationResponse;
import com.example.kinoxpbackend.entity.Reservation;
import com.example.kinoxpbackend.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping ("api/reservation")
public class ReservationController {

    ReservationService reservationService;

    @GetMapping
    List<ReservationResponse> getReservations(){
        return reservationService.findReservations();
    }

    @GetMapping(path= "/{id}")
    ReservationResponse getReservationById(@PathVariable int id) throws Exception {
        return reservationService.findReservationById(id);
    }

    @PostMapping
    ReservationResponse addReservation (@RequestBody ReservationRequest body){
        return reservationService.addReservation(body);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Boolean> editReservation (@RequestBody ReservationRequest body, @PathVariable int id){
        reservationService.editReservation (body, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    void deleteReservation( @PathVariable int id){
        reservationService.deleteReservation(id);
    }
}