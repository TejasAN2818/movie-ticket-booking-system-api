package com.example.mtb.service.impl;

import com.example.mtb.dto.movie_dto.MovieResponse;
import com.example.mtb.entity.Feedback;
import com.example.mtb.entity.Movie;
import com.example.mtb.excaption.MovieNotFoundByIdExcaption;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieResponse findMovieById(String movieId) {

        Optional<Movie> optionalMovie=movieRepository.findById(movieId);
        if (optionalMovie.isPresent()){
            Movie movie=optionalMovie.get();


            double avgRatings = 0;


            List<Feedback> feedbackList=movie.getFeedbackList();
            int noOfFeedBack=0;
            int totalReatings=0;
            if (feedbackList.isEmpty()){
                avgRatings=0.0;
            }else {
                for (Feedback feedback : feedbackList){
                    noOfFeedBack++;
                    totalReatings=totalReatings+feedback.getRatings();

                }
                avgRatings=totalReatings/noOfFeedBack;
            }

            return new MovieResponse(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDescripation(),
                    movie.getCast(),
                    movie.getRuntime(),
                    movie.getCertificate().toString(),
                    movie.getGenre().toString(),
                    avgRatings


            );
        }else{
            throw new MovieNotFoundByIdExcaption("Movie not found by Id");
        }
    }
}
