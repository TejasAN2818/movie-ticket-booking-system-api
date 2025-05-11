package com.example.mtb.controller;

import com.example.mtb.dto.movie_dto.MovieResponse;
import com.example.mtb.service.MovieService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping
@ResponseBody
public class MovieController {

    private final MovieService movieService;
    private final RestResponseBuilder restResponseBuilder;

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<ResponseStructure<MovieResponse>> findMovie(@PathVariable String movieId){

            MovieResponse movieResponse = movieService.findMovieById(movieId);

            return restResponseBuilder.sucess(HttpStatus.FOUND, "Movie found successfully", movieResponse);


    }

}
