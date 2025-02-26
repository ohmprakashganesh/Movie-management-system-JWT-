package com.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.entities.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer>{

    
}
