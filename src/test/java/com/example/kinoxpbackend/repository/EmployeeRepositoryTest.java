package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ShiftRepository shiftRepository;

    static Employee employee;

    @BeforeAll
    public static void setupData(@Autowired EmployeeRepository employeeRepository, @Autowired ShiftRepository shiftRepository) {
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

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employee = employee2;

        Shift shift1 = Shift.builder()
                .startTime(LocalDateTime.of(2022,10,10,10,10))
                .endTime(LocalDateTime.of(2022,10,10,18,30))
                .employee(employee1)
                .build();

        Shift shift2 = Shift.builder()
                .startTime(LocalDateTime.of(2022,10,12,9,10))
                .endTime(LocalDateTime.of(2022,10,12,15,30))
                .employee(employee2)
                .build();

        shiftRepository.save(shift1);
        shiftRepository.save(shift2);

    }


    @Test
    void existsByUserName() {
        assertTrue(employeeRepository.existsByUserName("JanMandenSSX"));
    }

    @Test
    void findByUserName() {
        Employee foundEmployee = employeeRepository.findByUserName("HanseManden123");
        assertEquals(employee.getId(), foundEmployee.getId());
    }
}