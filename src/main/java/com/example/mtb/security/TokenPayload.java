package com.example.mtb.security;

import java.time.Instant;
import java.util.Map;

public record TokenPayload(
        Map<String, Object> claims,
        String subject,
        Instant issuedAt,
        Instant expiration
) {
}
