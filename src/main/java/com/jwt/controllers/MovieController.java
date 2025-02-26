package com.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.dtos.MovieDTO;
import com.jwt.services.MovieServices;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MovieController {

    private final MovieServices movieServices;

    @RequestMapping("/add")
     public ResponseEntity<MovieDTO> AddMovie(@RequestBody MovieDTO movie ){
       MovieDTO dto= movieServices.postMovie(movie) ; 
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

  


    
}
