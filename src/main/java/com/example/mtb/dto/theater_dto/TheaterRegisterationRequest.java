package com.example.mtb.dto.theater_dto;

public record TheaterRegisterationRequest(
        String name,
        String address,
        String city,
        String landmark
) {
}
