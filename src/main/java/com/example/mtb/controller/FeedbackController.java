package com.example.mtb.controller;

import com.example.mtb.dto.feedback_dto.FeedbackRequest;
import com.example.mtb.dto.feedback_dto.FeedbackResponse;
import com.example.mtb.service.FeedbackService;
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
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/feedback")
    public ResponseEntity<ResponseStructure<FeedbackResponse>> createfeedback(@RequestBody FeedbackRequest feedback, @RequestParam String userId, @RequestParam UUID movieId){

        FeedbackResponse feedbackResponse=feedbackService.createFeedBack(feedback, userId, movieId);

        return restResponseBuilder.sucess(HttpStatus.CREATED, "give feedback sucessfully", feedbackResponse);

    }

}
