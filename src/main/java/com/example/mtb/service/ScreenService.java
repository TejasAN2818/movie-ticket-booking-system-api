package com.example.mtb.service;

import com.example.mtb.dto.screen_dto.ScreenRegisterationRequest;
import com.example.mtb.dto.screen_dto.ScreenResponse;
import com.example.mtb.dto.screen_dto.ScreenResponseList;
import com.example.mtb.dto.screen_dto.ScreenUpdateRequest;
import com.example.mtb.dto.seat_dto.SeatResponse;
import com.example.mtb.entity.Seat;

import java.util.List;

public interface ScreenService {
    ScreenResponse registerScreen(ScreenRegisterationRequest screen, String theaterId);

    ScreenResponse findScreenById(String screenId);

    //ScreenResponse updateScreenById(ScreenUpdateRequest updatedScreen, String screenId);
}
