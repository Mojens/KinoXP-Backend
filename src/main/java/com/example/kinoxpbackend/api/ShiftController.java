package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.ShiftRequest;
import com.example.kinoxpbackend.dto.ShiftResponse;
import com.example.kinoxpbackend.service.ShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/shifts")
public class ShiftController {

    ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping
    List<ShiftResponse> getShifts(){
        return shiftService.getShifts();
    }

    @GetMapping("/{id}")
    ShiftResponse getShiftById(@PathVariable int id) throws Exception{
        return shiftService.findShiftById(id);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void addShift(@RequestBody ShiftRequest e){
        LocalDateTime startTimeDate = LocalDateTime.parse(e.getStartTime());
        LocalDateTime endTimeDate = LocalDateTime.parse(e.getEndTime());
        shiftService.addShift(e.getEmployeeId(),startTimeDate,endTimeDate);
    }


    @PutMapping("/{shiftId}")
    ResponseEntity<Boolean> editShift(@RequestBody ShiftRequest e, @PathVariable int shiftId){
        shiftService.editShift(e,shiftId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteShiftById(@PathVariable int id){
        shiftService.deleteShiftById(id);
    }


}