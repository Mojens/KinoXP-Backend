package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.EmployeeRequest;
import com.example.kinoxpbackend.dto.EmployeeResponse;
import com.example.kinoxpbackend.dto.ShiftRequest;
import com.example.kinoxpbackend.dto.ShiftResponse;
import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import com.example.kinoxpbackend.repository.ShiftRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return shifts.stream().map(shift -> new ShiftResponse(shift, false)).toList();
    }

    public ShiftResponse findById(@PathVariable int id) throws Exception{
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

    public void editEmployee(EmployeeRequest employeeRequest, int id){
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: "+id+", cannot be found"));
        if (employeeRequest.getId() != id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find an Employee with given ID");
        }
        foundEmployee.setName(employeeRequest.getName());
        foundEmployee.setUserName(BCrypt.hashpw(employeeRequest.getUserName(),BCrypt.gensalt()));
        foundEmployee.setPassword(employeeRequest.getPassword());

        employeeRepository.save(foundEmployee);
    }

    public void deleteEmployeeById(@PathVariable int id){
        employeeRepository.deleteById(id);
    }


}
