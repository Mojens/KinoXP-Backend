package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.dto.ScreeningRequest;
import com.example.kinoxpbackend.dto.ScreeningResponse;
import com.example.kinoxpbackend.dto.ShiftRequest;
import com.example.kinoxpbackend.dto.ShiftResponse;
import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import com.example.kinoxpbackend.repository.ShiftRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ShiftServiceTest {


    static ShiftRepository shiftRepository;

    static EmployeeRepository employeeRepository;

    static Employee employee;

    static ShiftService shiftService;

    static Shift shift;

    static ShiftRequest shiftRequest;

    static int sizeOfRepo;


    @BeforeAll
    public static void setUpData(@Autowired ShiftRepository shift_Repository, @Autowired EmployeeRepository employee_Repository) {
        shiftRepository = shift_Repository;
        employeeRepository = employee_Repository;
        Employee e1 = Employee.builder()
                .id(1)
                .name("simon")
                .password("hej123")
                .userName("helokity")
                .build();

        Employee e2 = Employee.builder()
                .id(2)
                .name("jonas")
                .password("jona444")
                .userName("jonamand")
                .build();

        Employee e3 = Employee.builder()
                .id(3)
                .name("janne")
                .password("janjan123")
                .userName("jannedamen")
                .build();

        employeeRepository.save(e1);
        employeeRepository.save(e2);
        employeeRepository.save(e3);

        employee = e3;

        Shift shift1 = Shift.builder()
                .id(1)
                .startTime(LocalDateTime.of(2022, 9, 10, 23, 32, 32))
                .endTime(LocalDateTime.of(2022, 9, 18, 23, 32, 32))
                .employee(e1)
                .build();

        Shift shift2 = Shift.builder()
                .id(2)
                .startTime(LocalDateTime.of(2023, 9, 10, 20, 0, 32))
                .endTime(LocalDateTime.of(2023, 9, 18, 22, 30, 32))
                .employee(e2)
                .build();

        Shift shift3 = Shift.builder()
                .id(3)
                .startTime(LocalDateTime.of(2024, 9, 10, 10, 0, 32))
                .endTime(LocalDateTime.of(2024, 9, 18, 12, 30, 32))
                .employee(e2)
                .build();


        shiftRepository.save(shift1);
        shiftRepository.save(shift2);
        shiftRepository.save(shift3);
        shift = shift3;

        shiftService = new ShiftService(shiftRepository, employeeRepository);

        shiftRequest = new ShiftRequest(shift1);

        sizeOfRepo = shiftRepository.findAll().size();
    }

    @Test
    void getShifts() {
        List<ShiftResponse> shifts = shiftService.getShifts();
        assertEquals(sizeOfRepo,shifts.size());
        assertNotEquals(sizeOfRepo + 1, shifts.size());
    }

    @Test
    void addShift() {
        shiftService.addShift(employee.getId(), LocalDateTime.of(2023, 9, 10, 23, 32, 32), LocalDateTime.of(2023, 9, 10, 10, 32, 32));
        List<ShiftResponse> shiftResponses = shiftService.getShifts();
        assertEquals(sizeOfRepo + 1, shiftResponses.size());
        assertEquals(shiftResponses.get(3).getStartTime(), LocalDateTime.of(2023, 9, 10, 23, 32, 32));
        assertEquals(2, shiftResponses.get(1).getId());
        assertEquals(1, shiftResponses.get(0).getId());
    }

    @Test
    void editShift() {
        shiftService.editShift(shiftRequest, 3);
        List<ShiftResponse> shiftResponses = shiftService.getShifts();
        assertEquals(LocalDateTime.of(2022, 9, 10, 23, 32, 32), shiftResponses.get(2).getStartTime());
        assertEquals(LocalDateTime.of(2022, 9, 18, 23, 32, 32), shiftResponses.get(2).getEndTime());
    }

    @Test
    void deleteShiftById() {
        shiftService.deleteShiftById(2);
        List<ShiftResponse> shiftResponses = shiftService.getShifts();
        assertEquals(sizeOfRepo - 1, shiftResponses.size());
        assertEquals(1, shiftResponses.get(0).getEmployeeId());
    }
}