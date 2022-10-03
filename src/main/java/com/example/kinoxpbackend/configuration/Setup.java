package com.example.kinoxpbackend.configuration;

import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import com.example.kinoxpbackend.repository.ShiftRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;


@Controller
public class Setup implements ApplicationRunner {

  EmployeeRepository employeeRepository;
  ShiftRepository shiftRepository;

  public Setup(EmployeeRepository employeeRepository, ShiftRepository shiftRepository) {
    this.employeeRepository = employeeRepository;
    this.shiftRepository = shiftRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Employee employee1 = Employee.builder()
        .name("Jens")
        .type(4)
        .password("test123")
        .userName("JensAdmin")
        .build();
    Employee employee3 = Employee.builder()
        .name("Simon")
        .type(4)
        .password("test123")
        .userName("SimonOlsen")
        .build();

    Employee employee2 = Employee.builder()
        .name("Mo")
        .type(1)
        .password("test321")
        .userName("MoCasual")
        .build();

    Shift shift1 = Shift.builder()
        .startTime(LocalDateTime.now())
        .endTime(LocalDateTime.now().plusHours(8))
        .employee(employee1)
        .build();

    Shift shift2 = Shift.builder()
        .startTime(LocalDateTime.now().plusHours(8))
        .endTime(LocalDateTime.now().plusHours(16))
        .employee(employee2)
        .build();

    employeeRepository.save(employee1);
    employeeRepository.save(employee2);
    employeeRepository.save(employee3);

    shiftRepository.save(shift1);
    shiftRepository.save(shift2);





  }
}
