package com.jwt.dtos;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String refreshToken;
}
