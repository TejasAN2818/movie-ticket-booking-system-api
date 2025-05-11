package com.example.mtb.controller;

import com.example.mtb.dto.screen_dto.ScreenRegisterationRequest;
import com.example.mtb.dto.screen_dto.ScreenResponse;
import com.example.mtb.dto.screen_dto.ScreenResponseList;
import com.example.mtb.dto.screen_dto.ScreenUpdateRequest;
import com.example.mtb.dto.seat_dto.SeatResponse;
import com.example.mtb.entity.Seat;
import com.example.mtb.service.ScreenService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestErrorBuilder;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping
@RestController
@AllArgsConstructor
public class ScreenController<screenId> {

    private final ScreenService screenService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/screen/{theaterId}")
    public ResponseEntity<ResponseStructure<ScreenResponse>> registerScreen(@RequestBody ScreenRegisterationRequest screen, @PathVariable String theaterId){

        ScreenResponse screenResponse=screenService.registerScreen(screen, theaterId);

        return restResponseBuilder.sucess(HttpStatus.CREATED, "Screen register sucessfully", screenResponse);

    }

    @GetMapping("/screen/{screenId}")
    public ResponseEntity<ResponseStructure<ScreenResponse>> displayScreen(@PathVariable String screenId){
        ScreenResponse seatResponse=screenService.findScreenById(screenId);

        return restResponseBuilder.sucess(HttpStatus.FOUND, "seat desplay done", seatResponse);
    }

}
