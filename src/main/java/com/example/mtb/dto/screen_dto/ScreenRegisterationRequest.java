package com.example.mtb.dto.screen_dto;

import com.example.mtb.enums.ScreenType;

public record ScreenRegisterationRequest(
        ScreenType screenType,
        Integer capacity,
        Integer noOfRows
) {

}
