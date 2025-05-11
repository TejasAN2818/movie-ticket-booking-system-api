package com.example.mtb.dto.auth_dto;

import com.example.mtb.enums.UserRole;

import java.time.Instant;

public record AuthResponse(
        String userId,
        String username,
        String email,
        UserRole userRole,
        Instant accessExpiration,
        Instant refreshExpiration,
        String accessToken,
        String refreshToken
) {
}
