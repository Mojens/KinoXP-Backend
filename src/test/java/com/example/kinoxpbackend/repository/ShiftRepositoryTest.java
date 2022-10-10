package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ShiftRepositoryTest {

    @Autowired
    ShiftRepository shiftRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    static Employee employee;
    static String shiftId1;
    static String shiftId2;

    @BeforeAll
    public static void setUpData(@Autowired ShiftRepository shiftRepository,@Autowired EmployeeRepository employeeRepository){

    Employee s1 = Employee.builder()
            .name("simon")
            .password("hej123")
            .userName("helokity")
            .build();
    employeeRepository.save(s1);
    employee = s1;

    Shift shift1 = Shift.builder()
            .startTime(LocalDateTime.of(2022, 9, 10,23,32,32))
            .endTime(LocalDateTime.of(2022, 9, 18,23,32,32))
            .employee(employee)
            .build();

    shiftRepository.save(shift1);
    }

    @Test
    void existsByEmployee_IdAndStartTimeAndAndEndTime() {
        assertTrue(shiftRepository.existsByEmployee_IdAndStartTimeAndAndEndTime(employee.getId(), LocalDateTime.of(2022, 9, 10,23,32,32),LocalDateTime.of(2022, 9, 18,23,32,32)));
    }
}

