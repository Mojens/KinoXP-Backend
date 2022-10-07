package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.ShiftResponse;
import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import com.example.kinoxpbackend.repository.ShiftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ShiftServiceTest {

    @Autowired
    ShiftRepository shiftRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    static Employee employee;

    ShiftService shiftService;


    @BeforeEach
    public void setUpData(@Autowired ShiftRepository shiftRepository, @Autowired EmployeeRepository employeeRepository) {
        Employee s1 = Employee.builder()
                .name("simon")
                .password("hej123")
                .userName("helokity")
                .build();
        employeeRepository.save(s1);
        employee = s1;

        Shift shift1 = Shift.builder()
                .startTime(LocalDateTime.of(2022, 9, 10, 23, 32, 32))
                .endTime(LocalDateTime.of(2022, 9, 18, 23, 32, 32))
                .employee(employee)
                .build();
        shiftRepository.save(shift1);
        shiftService = new ShiftService(shiftRepository, employeeRepository);
    }

    @Test
    void getShifts() {
        List<ShiftResponse> shifts = shiftService.getShifts();
        assertEquals(1,shifts.size());
        assertNotEquals(3, shifts.size());
    }

    @Test
    void addShift() {
    }

    @Test
    void editShift() {
    }

    @Test
    void deleteShiftById() {
    }
}