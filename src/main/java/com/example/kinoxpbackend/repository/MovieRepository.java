package com.example.kinoxpbackend.repository;

import com.example.kinoxpbackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}

