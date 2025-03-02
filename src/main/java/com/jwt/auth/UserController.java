package com.jwt.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.AuthServices.AuthService;
import com.jwt.AuthServices.JwtService;
import com.jwt.AuthServices.RefreshTokenService;
import com.jwt.dtos.AuthResponse;
import com.jwt.dtos.LoginRequest;
import com.jwt.dtos.RefreshTokenRequest;
import com.jwt.dtos.RegisterRequest;
import com.jwt.entities.RefreshToken;
import com.jwt.entities.User;



@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {


    private final AuthService service;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public UserController(AuthService authService, RefreshTokenService refreshTokenService, JwtService jwtService) {
        this.service = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

     @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(service.register(registerRequest));
    }
      @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }

     @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build());
    }

    
}