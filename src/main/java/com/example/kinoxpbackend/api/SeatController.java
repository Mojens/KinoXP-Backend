package com.example.kinoxpbackend.api;


import com.example.kinoxpbackend.dto.SeatResponse;
import com.example.kinoxpbackend.dto.TheaterResponse;
import com.example.kinoxpbackend.entity.Seatings;
import com.example.kinoxpbackend.repository.SeatRepository;
import com.example.kinoxpbackend.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService){
        this.seatService = seatService;
    }

    @GetMapping
    public List<SeatResponse> getTheaters() {
       return seatService.getAllSeats();
    }

    @GetMapping(path = "/{id}")
    public SeatResponse getSeatByIdById(@PathVariable int id) throws Exception {

        return seatService.getSeatById(id);
    }

}
