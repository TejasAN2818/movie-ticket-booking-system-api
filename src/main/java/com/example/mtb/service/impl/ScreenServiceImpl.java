package com.example.mtb.service.impl;

import com.example.mtb.dto.screen_dto.ScreenRegisterationRequest;
import com.example.mtb.dto.screen_dto.ScreenResponse;
import com.example.mtb.dto.screen_dto.ScreenResponseList;
import com.example.mtb.dto.screen_dto.ScreenUpdateRequest;
import com.example.mtb.dto.seat_dto.SeatResponse;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Seat;
import com.example.mtb.entity.Theater;
import com.example.mtb.excaption.ScreenNotFoundByIdExcaption;
import com.example.mtb.excaption.TheaterNotFoundByIdExcaption;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.service.ScreenService;
import com.example.mtb.service.SeatServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterRepository theaterRepository;
    private final SeatServices seatServices;

    @Override
    public ScreenResponse registerScreen(ScreenRegisterationRequest screen, String theaterId) {

        Optional<Theater> optionalTheater = theaterRepository.findById(theaterId);
        Theater theater=optionalTheater.get();
        if (optionalTheater.isEmpty()) {
            throw new TheaterNotFoundByIdExcaption("theater not found by this Id");
        } else {
        }

        Screen newScreen = new Screen();
        newScreen.setScreenType(screen.screenType());
        newScreen.setCapacity(screen.capacity());
        newScreen.setNoOfRows(screen.noOfRows());
        newScreen.setCreatedAt(System.currentTimeMillis());
        newScreen.setTheater(theater);

        List<Screen> screenList=new ArrayList<Screen>();
        screenList.add(newScreen);
        theater.setScreens(screenList);

        theaterRepository.save(theater);
        Screen registeredScreen =screenRepository.save(newScreen);
        seatServices.generateseatLayout(newScreen);

       Optional<Screen> optionalNewRegisteredScreen=screenRepository.findById(registeredScreen.getScreenId());
       if (optionalNewRegisteredScreen.isEmpty()){
           throw new ScreenNotFoundByIdExcaption("Screen not found by this Id");
       }else {
           Screen newRegisteredScreen=optionalNewRegisteredScreen.get();

           List<Seat> seatList=newRegisteredScreen.getSeats();
           Collections.sort(seatList, Comparator.comparing(Seat::getSeatname));
           List<SeatResponse> seatResponsesList=new ArrayList<SeatResponse>();
           for (Seat seat : seatList){
               SeatResponse seatResponse=new SeatResponse(seat.getSeatId(), seat.getSeatname());
               seatResponsesList.add(seatResponse);

           }


           return new ScreenResponse(
                   newScreen.getScreenType(),
                   newScreen.getCapacity(),
                   newScreen.getNoOfRows(),
                   seatResponsesList
           );

       }


    }

    @Override
    public ScreenResponse findScreenById(String screenId) {

        Optional<Screen> optionalScreen=screenRepository.findById(screenId);
        if (optionalScreen.isEmpty()) {
            throw new ScreenNotFoundByIdExcaption("Screen not found this Id");
        }else {
            Screen screen = optionalScreen.get();
            List<Seat> seatList = screen.getSeats();
            Collections.sort(seatList, Comparator.comparing(Seat::getSeatname));

            List<SeatResponse> seatResponsesList=new ArrayList<>();
            for (Seat seat : seatList){
                SeatResponse seatResponse=new SeatResponse(seat.getSeatId(), seat.getSeatname());
                seatResponsesList.add(seatResponse);
            }

            return new ScreenResponse(
                    screen.getScreenType(),
                    screen.getCapacity(),
                    screen.getNoOfRows(),
                    seatResponsesList
            );
        }
    }

//    @Override
//    public ScreenResponse updateScreenById(ScreenUpdateRequest updatedScreen, String screenId) {
//
//        Optional<Screen> optionalScreen=screenRepository.findById(screenId);
//        if (optionalScreen.isEmpty()){
//            throw new ScreenNotFoundByIdExcaption("screen not found by this Id");
//        }else {
//            Screen existingScreen=optionalScreen.get();
//            existingScreen.setScreenType(updatedScreen.screenType());
//            existingScreen.setCapacity(updatedScreen.capacity());
//            existingScreen.setNoOfRows(updatedScreen.noOfRows());
//
//
//
//
//
//
//
//
//        }
//
//        return null;
//    }


}





