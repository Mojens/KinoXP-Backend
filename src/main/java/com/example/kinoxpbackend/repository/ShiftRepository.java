package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ShiftRepository extends JpaRepository<Shift,Integer> {

    boolean existsByEmployee_IdAndStartTimeAndAndEndTime(int employee_Id, LocalDateTime startTime,LocalDateTime endTime);
}
