package com.jwt.AuthServices;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.dtos.AuthResponse;
import com.jwt.dtos.LoginRequest;
import com.jwt.dtos.RegisterRequest;
import com.jwt.entities.User;
import com.jwt.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request){
        var user= User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .username(request.getUsername())
        .password(passwordEncoder.encode( request.getPassword()))
        .build();

        User savUser= userRepository.save(user);
        var accessToken=jwtService.generateToken(savUser);
        var refreshToken= refreshTokenService.createRefreshToken(savUser.getEmail());

        return AuthResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken.getRefreshToken())
        .name(savUser.getName())
        .email(savUser.getEmail())
        .build();
    }


 public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                        )
        );
 

        Optional< User> opt = userRepository.findByEmail(loginRequest.getEmail());
        if(opt.isPresent()){
            User user=opt.get();
            var accessToken = jwtService.generateToken(user);
            var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());
    
            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken.getRefreshToken())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }else{
            return null;
        }
        
     


    
}
}
