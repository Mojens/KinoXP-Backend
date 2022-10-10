package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.EmployeeRequest;
import com.example.kinoxpbackend.dto.EmployeeResponse;
import com.example.kinoxpbackend.dto.SeatResponse;
import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import com.example.kinoxpbackend.repository.ShiftRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeServiceTest {

    @Autowired
    EmployeeRepository employeeRepository;

    EmployeeService employeeService;

    Employee employee;

    @BeforeEach
    public void initData(@Autowired EmployeeRepository employeeRepository) {
        Employee employee1 = Employee.builder()
                .name("Jan")
                .password("JanERSej")
                .userName("JanMandenSSX")
                .type(1)
                .build();
        Employee employee2 = Employee.builder()
                .name("Hans")
                .password("HansERSej")
                .userName("HanseManden123")
                .type(2)
                .build();
        Employee employee3 = Employee.builder()
                .name("Jytte")
                .password("Jytte1111")
                .userName("JytteHelgen")
                .type(2)
                .build();
        Employee employee4 = Employee.builder()
                .name("Ole")
                .password("Ole1234")
                .userName("OleHansen")
                .type(1)
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employee = employee4;


        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void getEmployees() {
        List<Employee> listOfEmployees = employeeRepository.findAll();
        List<EmployeeResponse> foundEmployees = listOfEmployees.stream().map(employee -> new EmployeeResponse(employee, false)).toList();
        assertEquals(3, foundEmployees.size());
        assertNotEquals(4, foundEmployees.size());
    }

    @Test
    void findById() throws Exception {
        EmployeeResponse employeeResponse = employeeService.findById(2);
        assertEquals("Hans", employeeResponse.getName());
        assertEquals("HanseManden123", employeeResponse.getUserName());
    }

    @Test
    void addEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest(employee);
        employeeService.addEmployee(employeeRequest);
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();
        assertEquals(4, employeeResponses.size());
        assertEquals("OleHansen", employeeResponses.get(3).getUserName());
    }

    @Test
    void editEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest(employee);
        employeeService.editEmployee(employeeRequest, 1);
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();
        assertEquals(3, employeeResponses.size());
        assertEquals("Ole", employeeResponses.get(0).getName());
    }

    @Test
    void deleteEmployeeById() {
        employeeService.deleteEmployeeById(1);
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();
        assertEquals(2, employeeResponses.size());
        assertEquals("Hans", employeeResponses.get(0).getName());
    }

    @Test
    void employeeLogin() {
        Employee newEmployee = Employee.builder()
                .userName("JanMandenSSX")
                .password("JanERSej")
                .build();
        EmployeeRequest newEmployeeRequest = new EmployeeRequest(newEmployee);

        List<Employee> listOfEmployees = employeeRepository.findAll();
        Employee employeeToTest = listOfEmployees.get(0);
        EmployeeRequest trueEmployeeRequestToTest = new EmployeeRequest(employeeToTest);

        assertEquals(newEmployeeRequest.getUserName(),trueEmployeeRequestToTest.getUserName());

        assertEquals(BCrypt.checkpw("JanERSej",newEmployeeRequest.getPassword()),BCrypt.checkpw("JanERSej",trueEmployeeRequestToTest.getPassword()));;
    }
}