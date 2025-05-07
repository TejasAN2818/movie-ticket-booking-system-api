package com.example.mtb.service;

import com.example.mtb.dto.auth_dto.LoginRequest;

public interface AuthServices {
    String login(LoginRequest loginRequest);
}
