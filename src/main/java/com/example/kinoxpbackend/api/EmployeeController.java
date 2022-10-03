package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.EmployeeRequest;
import com.example.kinoxpbackend.dto.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/employees")
public class EmployeeController {


    @GetMapping
    List<EmployeeResponse> getEmployees(){
    }

    @GetMapping("/{id}")
    EmployeeResponse getEmployeeById(@PathVariable int id){

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EmployeeResponse addCar(@RequestBody EmployeeRequest e){

    }

    @PutMapping("/{id}")
    ResponseEntity<Boolean> editEmployee(@RequestBody EmployeeRequest e, @PathVariable int id){

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteEmployeeByID(@PathVariable int id){

    }





}
