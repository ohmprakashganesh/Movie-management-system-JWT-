package com.jwt.AuthServices;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jwt.entities.RefreshToken;
import com.jwt.entities.User;
import com.jwt.repositories.RefreshTokenRepository;
import com.jwt.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    
    public RefreshToken createRefreshToken(String username) {
         Optional <User>  opt = userRepository.findByEmail(username);
         if(opt.isPresent()){
            User user=opt.get();
      
            RefreshToken refreshToken=user.getRefreshToken();
    
            if(refreshToken == null){
                long refreshTokenValidity=30*100000;
                refreshToken=RefreshToken.builder()
                .refreshToken(UUID.randomUUID().toString())
                .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                .user(user)
                .build();
    
                refreshTokenRepository.save(refreshToken);
            }
            return refreshToken;
         }
         else{
            return null;
         }
      
    }

    public RefreshToken verifyRefreshToken(String refreshToken){
        RefreshToken refToken= refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new RuntimeException());

        if(refToken.getExpirationTime().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refToken);
            throw new RuntimeException("Refresh Token expired");
        }
        return refToken;
    
      
}
}
