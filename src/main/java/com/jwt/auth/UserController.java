package com.jwt.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.dtos.AuthResponse;
import com.jwt.dtos.RegisterRequest;
import com.jwt.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final AuthService service;

     @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@ModelAttribute RegisterRequest registerRequest) {
        return ResponseEntity.ok(service.registerUser(registerRequest));
    }

    
}