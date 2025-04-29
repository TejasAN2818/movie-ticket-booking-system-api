package com.example.mtb.service;

import com.example.mtb.dto.screen_dto.ScreenRegisterationRequest;
import com.example.mtb.dto.screen_dto.ScreenResponse;

public interface ScreenService {
    ScreenResponse registerScreen(ScreenRegisterationRequest screen, String theaterId);

    ScreenResponse findScreenById(String screenId);

    //ScreenResponse updateScreenById(ScreenUpdateRequest updatedScreen, String screenId);
}
