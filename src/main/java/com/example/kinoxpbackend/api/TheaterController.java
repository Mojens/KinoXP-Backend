package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.TheaterResponse;
import com.example.kinoxpbackend.service.TheaterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/theaters")
public class TheaterController {
    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping
    List<TheaterResponse> getTheaters() {
        return theaterService.getAllTheaters();
    }

    @GetMapping(path = "/{id}")
    public TheaterResponse getTheaterById(@PathVariable int id) throws Exception {
       return theaterService.getTheaterById(id);
    }

}
