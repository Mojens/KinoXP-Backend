package com.example.kinoxpbackend.api;


import com.example.kinoxpbackend.dto.TheaterResponse;
import com.example.kinoxpbackend.service.SeatService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService){
        this.seatService = seatService;
    }

    @GetMapping
    public void getTheaters() {
        seatService.getAllSeats();
    }

    @GetMapping(path = "/{id}")
    public SeatService getSeatByIdById(@PathVariable int id) throws Exception {
        return seatService.getSeatById(id);
    }

}
