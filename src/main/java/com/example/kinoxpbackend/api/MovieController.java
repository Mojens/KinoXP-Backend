package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.MovieRequest;
import com.example.kinoxpbackend.dto.MovieResponse;
import com.example.kinoxpbackend.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // get all movies
    @GetMapping
    public List<MovieResponse> getMovies(){
        return movieService.getAllMovies();
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
