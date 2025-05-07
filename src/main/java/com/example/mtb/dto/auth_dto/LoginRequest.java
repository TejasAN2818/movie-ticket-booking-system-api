package com.example.mtb.dto.auth_dto;

public record LoginRequest(
        String email,
        String password
) {
}
