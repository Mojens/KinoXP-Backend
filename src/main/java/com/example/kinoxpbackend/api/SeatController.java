package com.example.kinoxpbackend.api;


import com.example.kinoxpbackend.dto.SeatResponse;
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
    public SeatResponse getSeatById(@PathVariable int id) throws Exception {
        return seatService.getSeatById(id);
    }

    @GetMapping(path = "/theaterid/{id}")
    public List<SeatResponse> getSeatsByTheater(@PathVariable int id)throws Exception{
        System.out.println("test");
        return seatService.getSeatsByTheaterId(id);
    }

    

}
