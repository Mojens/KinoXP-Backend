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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeServiceTest {


    static EmployeeRepository employeeRepository;

    static EmployeeService employeeService;

    static Employee employee;

    static int sizeOfRepo;

    @BeforeAll
    public static void initData(@Autowired EmployeeRepository employee_Repository) {
        employeeRepository = employee_Repository;

        Employee employee1 = Employee.builder()
                .id(1)
                .name("Jan")
                .password("JanERSej")
                .userName("janmandenssx")
                .type(1)
                .shifts(new ArrayList<>())
                .build();
        Employee employee2 = Employee.builder()
                .id(2)
                .name("Hans")
                .password("HansERSej")
                .userName("hansemanden123")
                .type(2)
                .shifts(new ArrayList<>())
                .build();
        Employee employee3 = Employee.builder()
                .id(3)
                .name("Jytte")
                .password("Jytte1111")
                .userName("jyttehelgen")
                .type(2)
                .shifts(new ArrayList<>())
                .build();
        Employee employee4 = Employee.builder()
                .name("Ole")
                .password("Ole1234")
                .userName("olehansen")
                .type(1)
                .shifts(new ArrayList<>())
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employee = employee4;

        sizeOfRepo = employeeRepository.findAll().size();
    }

    @BeforeEach
    public void setEmployeeServiceUp(){
        employeeService = new EmployeeService(employeeRepository);
    }
    @Test
    void getEmployees() {
        List<Employee> listOfEmployees = employeeRepository.findAll();
        List<EmployeeResponse> foundEmployees = listOfEmployees.stream().map(employee -> new EmployeeResponse(employee, false)).toList();
        assertEquals(listOfEmployees.size(), foundEmployees.size());
        assertNotEquals(listOfEmployees.size()+1, foundEmployees.size());
    }

    @Test
    void findById() throws Exception {
        EmployeeResponse employeeResponse = employeeService.findById(2);
        assertEquals("Hans", employeeResponse.getName());
        assertEquals("hansemanden123", employeeResponse.getUserName().toLowerCase());
    }

    @Test
    void addEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest(employee);
        employeeService.addEmployee(employeeRequest);
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();
        assertEquals(sizeOfRepo + 1, employeeResponses.size());
        assertEquals("olehansen", employeeResponses.get(3).getUserName());

    }

    @Test
    void editEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest(employee);
        employeeService.editEmployee(employeeRequest, 1);
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();
        assertEquals(sizeOfRepo, employeeResponses.size());
        assertEquals("Ole", employeeResponses.get(0).getName());
    }

    @Test
    void deleteEmployeeById() {
        employeeService.deleteEmployeeById(1);
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();
        assertEquals(sizeOfRepo - 1, employeeResponses.size());
        assertEquals("Hans", employeeResponses.get(0).getName());
    }

    @Test
    void employeeLogin() {
        Employee newEmployee = Employee.builder()
                .userName("janmandenssx")
                .password("JanERSej")
                .build();
        EmployeeRequest newEmployeeRequest = new EmployeeRequest(newEmployee);

        List<Employee> listOfEmployees = employeeRepository.findAll();
        Employee employeeToTest = listOfEmployees.get(0);
        EmployeeRequest trueEmployeeRequestToTest = new EmployeeRequest(employeeToTest);

        assertEquals(newEmployeeRequest.getUserName(),trueEmployeeRequestToTest.getUserName().toLowerCase(Locale.ROOT));

        assertEquals(BCrypt.checkpw("JanERSej",newEmployeeRequest.getPassword()),BCrypt.checkpw("JanERSej",trueEmployeeRequestToTest.getPassword()));;
    }
}