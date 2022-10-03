package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.MovieResponse;
import com.example.kinoxpbackend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // GetAll movies
    public List<MovieReponse> getAllMovies() {
        return movieRepository.findAll().stream().map(MovieResponse::new).collect(Collectors.toList());
    }
}
