package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.EmployeeResponse;
import com.example.kinoxpbackend.dto.ScreeningRequest;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.entity.Screening;
import com.example.kinoxpbackend.entity.Shift;
import com.example.kinoxpbackend.repository.MovieRepository;
import com.example.kinoxpbackend.repository.ScreeningRepository;
import com.example.kinoxpbackend.repository.TheaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;

    public ScreeningService(ScreeningRepository screeningRepository, MovieRepository movieRepository, TheaterRepository theaterRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
    }

    // GetAll screenings
    public List<ScreeningResponse> getAllScreenings() {
        List<Screening> screeningList = screeningRepository.findAll();
        if (screeningList.size() < 1){
            System.out.println("No list");
        }
        else if (screeningList.size() > 1) {
            System.out.println("There is a List");
            for (Screening s: screeningList
                 ) {
                System.out.println(s);
            }
        }
        // sort by date

        return screeningList.stream().map(screening -> new ScreeningResponse(screening)).toList();
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
        newScreening = screeningRepository.save(newScreening);

        //Screening newScreening = ScreeningRequest.getScreeningEntity(screeningRequest);
        screeningRepository.save(newScreening);


        return new ScreeningResponse(newScreening);
    }

    // edit screening
    public void editScreening(ScreeningRequest screeningRequest, int id) {
        Screening screening = screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("Screening not found"));

        screening.setPerformance(screeningRequest.getPerformance());
        screening.setStartTime(screeningRequest.getStartTime());
        screening.setEndTime(screeningRequest.getEndTime());

        screeningRepository.save(screening);
    }

    // delete screening
    public void deleteScreening(@PathVariable int id) {
        if (!screeningRepository.existsScreeningById(id)) {
            throw new RuntimeException("Screening not found");
        }
        screeningRepository.deleteById(id);
    }
}
