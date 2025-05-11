package com.example.mtb.service;

import com.example.mtb.dto.auth_dto.AuthResponse;
import com.example.mtb.dto.auth_dto.LoginRequest;

public interface AuthServices {
    String login(LoginRequest loginRequest);

    AuthResponse refreshToken(LoginRequest loginRequest);
}
