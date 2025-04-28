package com.example.mtb.dto.screen_dto;

import com.example.mtb.enums.ScreenType;

public record ScreenUpdateRequest(
        ScreenType screenType,
        Integer capacity,
        Integer noOfRows
) {
}
