package com.example.kinoxpbackend.service;


import com.example.kinoxpbackend.dto.ShiftRequest;
import com.example.kinoxpbackend.dto.ShiftResponse;
import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import com.example.kinoxpbackend.repository.ShiftRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShiftService {
    ShiftRepository shiftRepository;
    EmployeeRepository employeeRepository;

    public ShiftService(ShiftRepository shiftRepository, EmployeeRepository employeeRepository ) {
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<ShiftResponse> getShifts(){
        List<Shift> shifts = shiftRepository.findAll();
        return shifts.stream().map(shift -> new ShiftResponse(shift, true)).toList();
    }

    public ShiftResponse findShiftById(@PathVariable int id) throws Exception{
        Shift foundShift = shiftRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift with ID: "+id+", cannot be found"));
        return new ShiftResponse(foundShift, true);
    }

    public void addShift(int employeeID, LocalDateTime startTime, LocalDateTime endTime) {
        Employee foundEmployee = employeeRepository.findById(employeeID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + employeeID + ", cannot be found"));
        if (shiftRepository.existsByEmployee_IdAndStartTimeAndAndEndTime(foundEmployee.getId(), startTime, endTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The employee already have a task at that time");
        }
        Shift createdShift = Shift.builder()
                .employee(foundEmployee)
                .startTime(startTime)
                .endTime(endTime)
                .build();
        shiftRepository.save(createdShift);
    }

    public void editShift(ShiftRequest shiftRequest, int shiftId){
        Shift foundShift = shiftRepository.findById(shiftId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift with ID: " + shiftId + ", cannot be found"));


        LocalDateTime startTimeDate = LocalDateTime.parse(shiftRequest.getStartTime());
        LocalDateTime endTimeDate = LocalDateTime.parse(shiftRequest.getEndTime());
        Employee foundEmployee = employeeRepository.findById(shiftRequest.getEmployeeId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + shiftRequest.getEmployeeId() + ", cannot be found"));

        foundShift.setStartTime(startTimeDate);
        foundShift.setEndTime(endTimeDate);
        foundShift.setEmployee(foundEmployee);


        shiftRepository.save(foundShift);
    }

    public void deleteShiftById(@PathVariable int id){
        shiftRepository.deleteById(id);
    }


}
