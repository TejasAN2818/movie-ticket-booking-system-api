package com.example.mtb.dto;

import java.time.LocalDate;

public record UserRequest(
        String username,
        String phoneNumber,
        LocalDate dateOfBirth
) {
}

