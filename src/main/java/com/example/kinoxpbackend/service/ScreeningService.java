package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.ScreeningRequest;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    // GetAll screenings
    public List<ScreeningResponse> getAllScreenings() {
        return screeningRepository.findAll().stream().map(ScreeningResponse::new).collect(Collectors.toList());
    }
    // get screening by id
    public ScreeningResponse getScreeningById(@PathVariable int id) {
        return new ScreeningResponse(screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screening not found")));
    }

    // add screening
    public ScreeningResponse addScreening(ScreeningRequest screeningRequest) {
        if (screeningRepository.existsScreeningById(screeningRequest.getId())) {
            throw new RuntimeException("Screening with this ID already exist");
        }
        Screening newScreening = ScreeningRequest.getScreeningEntity(screeningRequest);
        screeningRepository.save(newScreening);

        return new ScreeningResponse(newScreening);
    }

    // edit screening
    public void editScreening(ScreeningRequest screeningRequest, int id) {
        Screening screening = screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screening not found"));
        if (screeningRequest.getId()!=id) {
            throw new RuntimeException("Screening ID does not match");
        }
        screening.setPerformance(screeningRequest.getPerformance());
        screening.setStartTime(screeningRequest.getStartTime());
        screening.setEndTime(screeningRequest.getEndTime());
    }

    // delete screening
    public void deleteScreening(@PathVariable int id) {
        if (!screeningRepository.existsScreeningById(id)) {
            throw new RuntimeException("Screening not found");
        }
        screeningRepository.deleteById(id);
    }
}
