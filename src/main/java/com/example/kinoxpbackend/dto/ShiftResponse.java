package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Shift;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShiftResponse {

  private int id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
  private LocalDateTime startTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
  private LocalDateTime endTime;

  private int employeeId;

  public ShiftResponse (Shift shift, boolean includeAll){
    this.employeeId = shift.getEmployee().getId();
    this.startTime = shift.getStartTime();
    this.endTime = shift.getEndTime();
    if (includeAll){
      this.id = shift.getId();
    }
  }
}
