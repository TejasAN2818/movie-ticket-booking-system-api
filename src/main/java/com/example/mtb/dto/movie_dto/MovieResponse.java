package com.example.mtb.dto.movie_dto;

import com.example.mtb.enums.Certificate;
import com.example.mtb.enums.Genre;

import java.util.Set;
import java.util.UUID;

public record MovieResponse(
        UUID movieId,
        String title,
        String descripation,
        Set<String> cast,
        long runtime,
        String certificate,
        String genre,
        double avarageRatings
) {
}
