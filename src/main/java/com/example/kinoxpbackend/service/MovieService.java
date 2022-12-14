package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.MovieRequest;
import com.example.kinoxpbackend.dto.MovieResponse;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;

    public MovieService(MovieRepository movieRepository, ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
    }

    // GetAll movies
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(MovieResponse::new).collect(Collectors.toList());
    }
    // Get movie by id
    public MovieResponse getMovieById(@PathVariable int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        return new MovieResponse(movie);
    }
    // add movie
    public MovieResponse addMovie(MovieRequest movieRequest) {
        if(movieRepository.existsMovieById(movieRequest.getId())) {
            throw new RuntimeException("Movie with this ID already exist");
        }

        Movie newMovie = MovieRequest.getMovieEntity(movieRequest);
        newMovie = movieRepository.save(newMovie);

        return new MovieResponse(newMovie);
    }

    // Edit Movie
    public void editMovie(MovieRequest movieRequest, int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie with this ID does not exist"));

        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setRating(movieRequest.getRating());
        movie.setGenre(movieRequest.getGenre());
        movie.setDuration(movieRequest.getDuration());
        movie.setAgeLimit(movieRequest.getAgeLimit());
        movie.setPrice(movieRequest.getPrice());
        movie.setPhoto(movieRequest.getPhoto());
        movie.setStars(movieRequest.getStars());
        movie.setTrailers(movieRequest.getTrailers());
        movie.setShowStartDate(movieRequest.getShowStartDate());
        movie.setShowEndDate(movieRequest.getShowEndDate());


        movieRepository.save(movie);
    }

    // Delete Movie
    public void deleteMovie(@PathVariable int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie with this ID does not exist"));
        movieRepository.delete(movie);
    }

    // Get movies with all its screenings
    public List<MovieResponse> getMoviesWithScreenings() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse> response = movies.stream().map(MovieResponse::new).collect(Collectors.toList());
        // sort screenings by date
        return response;

    }



}
