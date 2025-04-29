package com.example.mtb.controller;

import com.example.mtb.dto.screen_dto.ScreenRegisterationRequest;
import com.example.mtb.dto.screen_dto.ScreenResponse;
import com.example.mtb.service.ScreenService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping
@RestController
@AllArgsConstructor
public class ScreenController<screenId> {

    private final ScreenService screenService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/screen")
    public ResponseEntity<ResponseStructure<ScreenResponse>> registerScreen(@RequestBody ScreenRegisterationRequest screen, @RequestParam String theaterId){

        ScreenResponse screenResponse=screenService.registerScreen(screen, theaterId);

        return restResponseBuilder.sucess(HttpStatus.CREATED, "Screen register sucessfully", screenResponse);

    }

    @GetMapping("/screen")
    public ResponseEntity<ResponseStructure<ScreenResponse>> displayScreen(@RequestParam String screenId){
        ScreenResponse seatResponse=screenService.findScreenById(screenId);

        return restResponseBuilder.sucess(HttpStatus.FOUND, "seat desplay done", seatResponse);
    }

//    @PutMapping
//    public ResponseEntity<ResponseStructure<ScreenResponse>> updateScreen(@RequestBody ScreenUpdateRequest updatedScreen, @RequestParam String screenId){
//
//        ScreenResponse screenResponse=screenService.updateScreenById(updatedScreen, screenId);
//    }

}
