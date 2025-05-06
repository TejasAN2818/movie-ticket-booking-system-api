package com.example.mtb.dto.show_dto;

import java.time.Instant;

public record ShowListResponse(
        String showId,
        Instant startAt,
        Instant endAt
) {
}
