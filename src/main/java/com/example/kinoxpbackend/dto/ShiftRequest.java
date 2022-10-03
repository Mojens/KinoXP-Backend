package com.example.kinoxpbackend.dto;

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

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private int employeeId;


}
