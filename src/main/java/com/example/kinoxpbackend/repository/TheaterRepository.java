package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {

    boolean existsTheaterById(int id);
    Theater findTheaterById(int id);

}

