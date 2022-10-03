package com.example.kinoxpbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



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
