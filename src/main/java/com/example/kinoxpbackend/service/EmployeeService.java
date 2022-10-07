package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.EmployeeRequest;
import com.example.kinoxpbackend.dto.EmployeeResponse;
import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponse> getEmployees(){
        List<Employee> employees = employeeRepository.findAll();
         return employees.stream().map(employee -> new EmployeeResponse(employee, false)).toList();
    }

    public EmployeeResponse findById(@PathVariable int id) throws Exception{
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: "+id+", cannot be found"));
        return new EmployeeResponse(foundEmployee, true);
    }

    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest){
        if (employeeRepository.existsById(employeeRequest.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee with this ID already exists");
        }
        Employee newEmployee = EmployeeRequest.getEmployeeEntity(employeeRequest);
        employeeRepository.save(newEmployee);

        return new EmployeeResponse(newEmployee, true);
    }

    public void editEmployee(EmployeeRequest employeeRequest, int id){
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: "+id+", cannot be found"));

        foundEmployee.setName(employeeRequest.getName());
        foundEmployee.setUserName(BCrypt.hashpw(employeeRequest.getUserName(),BCrypt.gensalt()));
        foundEmployee.setPassword(employeeRequest.getPassword());

        employeeRepository.save(foundEmployee);
    }

    public void deleteEmployeeById(@PathVariable int id){
        employeeRepository.deleteById(id);
    }

    public EmployeeResponse employeeLogin(EmployeeRequest e){
        if (!employeeRepository.existsByUserName(e.getUserName())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Employee foundEmployee = employeeRepository.findByUserName(e.getUserName());
        if (BCrypt.checkpw(e.getPassword(),foundEmployee.getPassword())){
            return new EmployeeResponse(foundEmployee,true);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password");

    }
}
