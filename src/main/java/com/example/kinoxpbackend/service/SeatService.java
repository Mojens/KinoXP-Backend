package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.repository.SeatRepository;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    public void getAllSeats() {
    }

    public SeatService getSeatById(int id) {
        return null;
    }
}
