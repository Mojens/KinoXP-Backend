package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ShiftRepository shiftRepository;

    @BeforeEach
    public void setupData(@Autowired EmployeeRepository employeeRepository, @Autowired ShiftRepository shiftRepository) {
        Employee employee = Employee.builder()
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
    }


    @Test
    void existsByUserName() {
    }

    @Test
    void findByUserName() {
    }
}