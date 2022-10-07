package com.example.kinoxpbackend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shift {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @Column(nullable = false)
  private LocalDateTime startTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @Column(nullable = false)
  private LocalDateTime endTime;

  @ManyToOne
  private Employee employee;







}
