package com.example.mtb.dto.show_dto;

import java.time.Instant;

public record ShowResponse(
        Instant startAt,
        Instant endAt
) {
}
