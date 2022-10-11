package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Movie;
import com.example.kinoxpbackend.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    boolean existsMovieById(int id);
    Movie findMovieById(int id);


}

