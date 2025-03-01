package com.jwt.services;

import com.jwt.dtos.AuthResponse;
import com.jwt.dtos.LoginRequest;
import com.jwt.dtos.RegisterRequest;

public interface AuthService {

    AuthResponse registerUser(RegisterRequest request);

    AuthResponse loginUser(LoginRequest request);

    
}
