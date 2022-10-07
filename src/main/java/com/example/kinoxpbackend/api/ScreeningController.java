package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.MovieRequest;
import com.example.kinoxpbackend.dto.ScreeningRequest;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.service.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/screenings")
public class ScreeningController {
    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }
    // Get all screenings
    @GetMapping
    public List<ScreeningResponse> getScreenings() {
        return screeningService.getAllScreenings();
    }

    // Get screening by id
    @GetMapping(path = "/{id}")
    public ScreeningResponse getScreeningById(@PathVariable int id) throws Exception {
        return screeningService.getScreeningById(id);
    }

    // Add screening
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ScreeningResponse addScreening(@RequestBody ScreeningRequest screening) {
        return screeningService.addScreening(screening);
    }

    // Edit screening
    @PutMapping(path = "/{id}")
    ResponseEntity<Boolean> editScreening(@RequestBody ScreeningRequest screening, @PathVariable int id) {
        screeningService.editScreening(screening, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // Delete screening
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteScreening(@PathVariable int id) {
         screeningService.deleteScreening(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/all")
    public ScreeningResponse addMultiScreening(@RequestBody ScreeningRequest screening) {

        return screeningService.addScreeningsToMovie(screening);
    }




}
