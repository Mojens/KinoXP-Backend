package com.example.kinoxpbackend.api;

import com.example.kinoxpbackend.dto.EmployeeRequest;
import com.example.kinoxpbackend.dto.EmployeeResponse;
import com.example.kinoxpbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    List<EmployeeResponse> getEmployees(){
        return employeeService.getEmployees();
    }


    @GetMapping("/{id}")
    EmployeeResponse getEmployeeById(@PathVariable int id) throws Exception{
        return employeeService.findById(id);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EmployeeResponse addCar(@RequestBody EmployeeRequest e){
        return employeeService.addEmployee(e);
    }

    @PutMapping("/{id}")
    ResponseEntity<Boolean> editEmployee(@RequestBody EmployeeRequest e, @PathVariable int id){
        employeeService.editEmployee(e, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteEmployeeByID(@PathVariable int id){
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping("/login")
    EmployeeResponse employeeLogin(@RequestBody EmployeeRequest e){
        return employeeService.employeeLogin(e);
    }

}
