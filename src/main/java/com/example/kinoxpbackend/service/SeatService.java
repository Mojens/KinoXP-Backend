package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.SeatResponse;
import com.example.kinoxpbackend.entity.Seatings;
import com.example.kinoxpbackend.repository.SeatRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    // Get all seats
    public List<SeatResponse> getAllSeats() {
        return seatRepository.findAll().stream().map(SeatResponse::new).collect(Collectors.toList());
    }

    // Get seat by id

    public SeatResponse getSeatById(@PathVariable int id) throws Exception {
        Seatings seat = seatRepository.findById(id).orElseThrow(() -> new RuntimeException("Seat not found"));
        return new SeatResponse(seat);
    }
}

