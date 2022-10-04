package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.SeatChoiceResponse;
import com.example.kinoxpbackend.entity.SeatChoice;
import com.example.kinoxpbackend.repository.SeatChoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatChoiceService {
    private final SeatChoiceRepository seatChoiceRepository;

    public SeatChoiceService(SeatChoiceRepository seatChoiceRepository) {this.seatChoiceRepository = seatChoiceRepository;}

    public List<SeatChoiceResponse> getAllSeatChoices() {
        return seatChoiceRepository.findAll().stream().map(SeatChoiceResponse::new).collect(Collectors.toList());
    }

    public SeatChoiceResponse getSeatChoiceById(@PathVariable int id) {
        return new SeatChoiceResponse(seatChoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("SeatChoice not found")));
    }

}
