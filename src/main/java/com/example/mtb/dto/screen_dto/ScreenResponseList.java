package com.example.mtb.dto.screen_dto;

import com.example.mtb.dto.seat_dto.SeatResponse;
import com.example.mtb.enums.ScreenType;

import java.util.List;

public record ScreenResponseList(
        ScreenType screenType,
        Integer capacity,
        Integer noOfRows,
        List<SeatResponse> seats
) {
}
