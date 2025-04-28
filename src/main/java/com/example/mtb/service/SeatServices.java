package com.example.mtb.service;

import com.example.mtb.dto.seat_dto.SeatResponse;
import com.example.mtb.entity.Screen;

import java.util.List;

public interface SeatServices {

    void generateseatLayout(Screen screen);

   // List<SeatResponse> displayAllSeatByScreenId(String screenId);
}
