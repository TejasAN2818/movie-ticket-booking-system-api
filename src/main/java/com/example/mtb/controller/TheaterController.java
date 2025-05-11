package com.example.mtb.controller;

import com.example.mtb.dto.theater_dto.TheaterRegisterationRequest;
import com.example.mtb.dto.theater_dto.TheaterRequest;
import com.example.mtb.dto.theater_dto.TheaterResponse;
import com.example.mtb.entity.Theater;
import com.example.mtb.service.TheaterService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping
@ResponseBody
public class TheaterController {

    private final TheaterService theaterService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/theater/{email}")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<ResponseStructure<TheaterResponse>> registerTheater(@RequestBody TheaterRegisterationRequest theater, @PathVariable String email){
        TheaterResponse theaterResponse=theaterService.registerTheater(theater, email);

        return restResponseBuilder.sucess(HttpStatus.CREATED, "Theater register sucessfully", theaterResponse);
    }

    @PutMapping("/theater/{theaterId}")
    public  ResponseEntity<ResponseStructure<String>> updateTheater(@RequestBody TheaterRequest updatedTheater, @PathVariable String theaterId){
        String str=theaterService.updateTheaterById(updatedTheater, theaterId);

        return restResponseBuilder.sucess(HttpStatus.OK, "theater updated sucessfully", str);
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<ResponseStructure<TheaterResponse>> findTheaterById(@PathVariable String theaterId){
        TheaterResponse theaterResponse=theaterService.findTheaterById(theaterId);

        return restResponseBuilder.sucess(HttpStatus.FOUND, "theater display secussfully", theaterResponse);

    }


}
