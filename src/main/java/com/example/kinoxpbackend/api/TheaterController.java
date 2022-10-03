package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.TheaterResponse;
import com.example.kinoxpbackend.service.TheaterService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/theaters")
public class TheaterController {
    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping
    public void getTheaters() {
        theaterService.getAllTheaters();
    }

    @GetMapping(path = "/{id}")
    public TheaterResponse getTheaterById(@PathVariable int id) throws Exception {
       return theaterService.getTheaterById(id);
    }

}
