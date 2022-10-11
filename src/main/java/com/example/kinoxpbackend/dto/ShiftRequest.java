package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Shift;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftRequest {

  private int id;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private int employeeId;

  public ShiftRequest(Shift s) {
    this.id = s.getId();
    this.startTime = s.getStartTime();
    this.endTime = s.getEndTime();
    this.employeeId = s.getEmployee().getId();
  }
}
