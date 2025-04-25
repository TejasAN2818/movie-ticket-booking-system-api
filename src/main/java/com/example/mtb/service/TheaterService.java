package com.example.mtb.service;

import com.example.mtb.dto.theater_dto.TheaterRegisterationRequest;
import com.example.mtb.dto.theater_dto.TheaterRequest;
import com.example.mtb.dto.theater_dto.TheaterResponse;

public interface TheaterService {

    TheaterResponse registerTheater(TheaterRegisterationRequest theater, String theaterOwnerEmail);

    String updateTheaterById(TheaterRequest updatedTheater, String theaterId);

    TheaterResponse findTheaterById(String theaterId);
}
