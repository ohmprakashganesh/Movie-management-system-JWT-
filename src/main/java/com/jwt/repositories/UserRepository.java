package com.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.entities.User;

public interface UserRepository extends JpaRepository<User ,Integer>{
    

    
} 
