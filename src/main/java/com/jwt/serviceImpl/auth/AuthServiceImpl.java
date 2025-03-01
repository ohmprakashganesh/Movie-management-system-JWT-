package com.jwt.serviceImpl.auth;


import org.springframework.stereotype.Service;

import com.jwt.dtos.AuthResponse;
import com.jwt.dtos.LoginRequest;
import com.jwt.dtos.RegisterRequest;
import com.jwt.entities.User;
import com.jwt.enumPack.UserRole;
import com.jwt.repositories.UserRepository;
import com.jwt.services.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse registerUser(RegisterRequest request) {
        var user=User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .username(request.getUsername())
        .password(request.getPassword())
        .role(UserRole.USDR)
        .build();

         System.out.println("user is sved "+userRepository.save(user));
     return null;
   }

    @Override
    public AuthResponse loginUser(LoginRequest request) {
        return null;
  }
    
}
