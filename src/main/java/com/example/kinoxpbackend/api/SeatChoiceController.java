package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.ScreeningRequest;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.dto.SeatChoiceRequest;
import com.example.kinoxpbackend.dto.SeatChoiceResponse;
import com.example.kinoxpbackend.service.SeatChoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/seatchoices")
public class SeatChoiceController {
    private final SeatChoiceService seatChoiceService;

    public SeatChoiceController(SeatChoiceService seatChoiceService) {this.seatChoiceService = seatChoiceService;}

    @GetMapping
    List<SeatChoiceResponse> getSeatChoiceResponses() {return seatChoiceService.getAllSeatChoices();}

    @GetMapping(path = "/{id}")
    SeatChoiceResponse getSeatChoiceById(@PathVariable int id) throws Exception {
        return seatChoiceService.getSeatChoiceById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    SeatChoiceResponse addSeatChoice(@RequestBody SeatChoiceRequest seatChoiceRequest) {
        return seatChoiceService.addSeatChoice(seatChoiceRequest);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Boolean> editSeatChoice(@RequestBody SeatChoiceRequest seatChoice, @PathVariable int id) {
        seatChoiceService.editSeatChoice(seatChoice, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteSeatChoice(@PathVariable int id) {
        seatChoiceService.deleteSeatChoice(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
