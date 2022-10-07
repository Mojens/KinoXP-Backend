package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.MovieRequest;
import com.example.kinoxpbackend.dto.MovieResponse;
import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.service.MovieService;
import com.example.kinoxpbackend.service.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/movies")
public class MovieController {

    MovieService movieService;
    ScreeningService screeningService;

    public MovieController(MovieService movieService, ScreeningService screeningService) {
        this.movieService = movieService;
        this.screeningService = screeningService;
    }

    // get all movies
    @GetMapping
    List<MovieResponse> getMoviesWithScreenings(){
        screeningService.updateAllPerformance();
        return movieService.getMoviesWithScreenings();
    }


    // get movie by id
    @GetMapping(path = "/{id}")
    public MovieResponse getMovieById(@PathVariable int id) throws Exception {
        return movieService.getMovieById(id);
    }
    // Add movie

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieResponse addMovie(@RequestBody MovieRequest movie) {
        return movieService.addMovie(movie);
    }
    // Delete movie by id
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Boolean> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }
    // Edit movie
    @PutMapping(path = "/{id}")
    ResponseEntity<Boolean> editMovie(@RequestBody MovieRequest movie,@PathVariable int id) {
        movieService.editMovie(movie, id);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }




}
