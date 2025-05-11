package com.example.mtb.controller;

import com.example.mtb.dto.show_dto.AllShowResponse;
import com.example.mtb.dto.show_dto.ShowResponse;
import com.example.mtb.service.ShowService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping
@ResponseBody
public class ShowController {

    private final ShowService showService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/theater/{theaterId}/screen/{screenId}/movie/{movieId}")
    public ResponseEntity<ResponseStructure<ShowResponse>> addShow(@PathVariable String theaterId,
                                                                   @PathVariable String screenId,
                                                                   @PathVariable String movieId,
                                                                   @RequestParam Instant startAt
                                                                   ){
        ShowResponse showResponse=showService.addShow(theaterId,screenId,movieId,startAt);

        return restResponseBuilder.sucess(HttpStatus.CREATED,"show created sucessfully", showResponse);
    }

    @GetMapping("/show/screen/{screenId}")
    public ResponseEntity<ResponseStructure<AllShowResponse>> displayAllShowByScreenId(@RequestParam  String screenId){

        AllShowResponse allShowResponses=showService.displayAllshow(screenId);

        return restResponseBuilder.sucess(HttpStatus.OK, "display all show done", allShowResponses);

    }




}
