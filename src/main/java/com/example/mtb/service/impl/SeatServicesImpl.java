package com.example.mtb.service.impl;

import com.example.mtb.dto.seat_dto.SeatResponse;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Seat;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.SeatRepository;
import com.example.mtb.service.SeatServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatServicesImpl implements SeatServices {

    private final SeatRepository seatRepository;
    private final ScreenRepository screenRepository;

    @Override
    public void generateseatLayout(Screen screen) {

        Screen existingScreen=screenRepository.findById(screen.getScreenId()).get();

        List<Seat> seatList=new ArrayList<Seat>();

        int noOfRows= screen.getNoOfRows();
        int seatsPerRow= screen.getCapacity()/noOfRows;

        char rowname='A';
        int colomNumber=1;
        for (int i=1; i<=noOfRows; i++){
            for (int j=1; j<=seatsPerRow; j++){

                Seat newSeat=new Seat();
                newSeat.setSeatname(rowname+String.valueOf(j));
                newSeat.setCreatedAt(System.currentTimeMillis());
                newSeat.setScreen(existingScreen);

                seatRepository.save(newSeat);
                seatList.add(newSeat);

            }
            rowname++;
        }

        existingScreen.setSeats(seatList);
        screenRepository.save(existingScreen);

    }

}
