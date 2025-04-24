package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserRegistrationResuest(
        @NotBlank(message = "Username is required")
        @Pattern(regexp = "^[A-Za-z]+$", message = "should contain only alphobatical char")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password should be at least 6 characters")
        String password,

        @NotNull(message = "User role is required")
        UserRole userRole,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
        String phoneNumber,

        @NotNull(message = "Date of birth is required")
        LocalDate dateOfBirth
        ) {

}