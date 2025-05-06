package com.example.mtb.service.impl;

import com.example.mtb.dto.show_dto.AllShowResponse;
import com.example.mtb.dto.show_dto.ShowListResponse;
import com.example.mtb.dto.show_dto.ShowResponse;
import com.example.mtb.entity.Movie;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import com.example.mtb.entity.Theater;
import com.example.mtb.excaption.MovieNotFoundByIdExcaption;
import com.example.mtb.excaption.ScreenNotFoundByIdExcaption;
import com.example.mtb.excaption.ShowTimeNotAvailableExcaption;
import com.example.mtb.excaption.TheaterNotFoundByIdExcaption;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.ShowRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.service.ShowService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final TheaterRepository theaterRepository;
    private final ScreenRepository screenRepository;
    private final MovieRepository movieRepository;


    @Override
    public ShowResponse addShow(String theaterId, String screenId, UUID movieId, Instant startAt) {

        Optional<Theater> optionalTheater=theaterRepository.findById(theaterId);
        if (optionalTheater.isEmpty()){
            throw new TheaterNotFoundByIdExcaption("theater not found");
        }else {
            Optional<Screen> optionalScreen=screenRepository.findById(screenId);
            if (optionalScreen.isEmpty()){
                throw new ScreenNotFoundByIdExcaption("screen not found");
            }else {
                Optional<Movie> optionalMovie=movieRepository.findById(movieId);
                if (optionalMovie.isEmpty()){
                    throw new MovieNotFoundByIdExcaption("movie not found");
                }else {
                    Movie existingMovie = optionalMovie.get();
                    Screen existingScreen=optionalScreen.get();
                    Theater existingTheater=optionalTheater.get();

                    long newShowStartTime=startAt.toEpochMilli();
                    Long movieRunTime=Duration.ofMinutes(existingMovie.getRuntime()).toMillis();
                    long newShowEndTime=newShowStartTime+movieRunTime;

                    List<Show> existingShowList =existingScreen.getShows();
                    for (Show existingShow : existingShowList){

                        if (existingShow.getStartAt() < newShowEndTime && existingShow.getEndAt() > newShowStartTime){
                            throw new ShowTimeNotAvailableExcaption("show will already scheduled at this time. please try another schedule. ");
                        }
                    }

                    Show newShow=new Show();
                    newShow.setStartAt(newShowStartTime);
                    newShow.setEndAt(newShowEndTime);
                    newShow.setMovie(existingMovie);
                    newShow.setScreen(existingScreen);
                    newShow.setTheater(existingTheater);

                    List<Show> showlist=new ArrayList<Show>();
                    showlist.add(newShow);

                    existingMovie.setShows(showlist);
                    existingScreen.setShows(showlist);
                    existingTheater.setShows(showlist);

                    movieRepository.save(existingMovie);
                    screenRepository.save(existingScreen);
                    theaterRepository.save(existingTheater);

                    Show show=showRepository.save(newShow);

                    return new ShowResponse(
                            Instant.ofEpochMilli(newShow.getStartAt()),
                            Instant.ofEpochMilli(newShow.getEndAt())
                            );
                }
            }
        }
    }

    @Override
    public AllShowResponse displayAllshow(String screenId) {

        Screen existingScreen = screenRepository.findById(screenId).get();

        List<ShowListResponse> showList = new ArrayList<ShowListResponse>();

        for (Show screen : existingScreen.getShows()) {
            ShowListResponse showListResponse = new ShowListResponse(screen.getShowId(), Instant.ofEpochMilli(screen.getStartAt()) , Instant.ofEpochMilli(screen.getEndAt()));
            showList.add(showListResponse);
        }


        return new AllShowResponse(showList);
    }
}
