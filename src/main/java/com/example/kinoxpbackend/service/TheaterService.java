package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.TheaterRequest;
import com.example.kinoxpbackend.dto.TheaterResponse;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    // GetAll theaters
    public List<TheaterResponse> getAllTheaters() {
        List<Theater> theaters = theaterRepository.findAll();
        return theaters.stream().map(theater -> new TheaterResponse(theater)).toList();
    }

    // Get theater by id
    public TheaterResponse getTheaterById(@PathVariable int id) {
        Theater theater = theaterRepository.findById(id).orElseThrow(() -> new RuntimeException("Theater not found"));
        return new TheaterResponse(theater);
    }

    // Add Theater
    public TheaterResponse addTheater(Theater theater) {
        if (theaterRepository.existsTheaterById(theater.getId())) {
            throw new RuntimeException("Theater with this ID already exist");
        }
        theater = theaterRepository.save(theater);
        return new TheaterResponse(theater);
    }


}
