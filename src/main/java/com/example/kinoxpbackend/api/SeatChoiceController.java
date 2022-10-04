package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.SeatChoiceResponse;
import com.example.kinoxpbackend.service.SeatChoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/seatchoices")
public class SeatChoiceController {
    private final SeatChoiceService seatChoiceService;

    public SeatChoiceController(SeatChoiceService seatChoiceService) {this.seatChoiceService = seatChoiceService;}

    @GetMapping
    public List<SeatChoiceResponse> getSeatChoiceResponses() {return seatChoiceService.getAllSeatChoices();}

    @GetMapping(path = "/{id}")
    public SeatChoiceResponse getSeatChoiceById(@PathVariable int id) throws Exception {
        return seatChoiceService.getSeatChoiceById(id);
    }
}
