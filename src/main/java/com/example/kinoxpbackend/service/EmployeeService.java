package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.EmployeeResponse;
import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


}
