package com.example.mtb.service;

import com.example.mtb.dto.show_dto.AllShowResponse;
import com.example.mtb.dto.show_dto.ShowResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ShowService {
    ShowResponse addShow(String theaterId, String screenId, UUID movieId, Instant startAt);

    AllShowResponse displayAllshow(String screenId);
}
