package com.jwt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.entities.User;

public interface UserRepository extends JpaRepository<User ,Integer>{

    Optional<User> findByEmail(String email);
    

    
} 
