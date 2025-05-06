package com.example.mtb.service;

import com.example.mtb.dto.movie_dto.MovieResponse;

import java.util.UUID;

public interface MovieService {
    MovieResponse findMovieById(UUID movieId);
}
