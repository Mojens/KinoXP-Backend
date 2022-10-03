package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Employee;
import com.example.kinoxpbackend.entity.Shift;
import com.example.kinoxpbackend.repository.EmployeeRepository;
import com.example.kinoxpbackend.service.ShiftService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftRequest {

  private int id;


  private String startTime;

  private String endTime;

  private int employeeId;
}
