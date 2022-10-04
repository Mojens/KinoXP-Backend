package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

    boolean existsScreeningById(int id);
    Screening findScreeningById(int id);


}
