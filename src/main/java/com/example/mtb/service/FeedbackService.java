package com.example.mtb.service;

import com.example.mtb.dto.feedback_dto.FeedbackRequest;
import com.example.mtb.dto.feedback_dto.FeedbackResponse;

import java.util.UUID;

public interface FeedbackService {
    FeedbackResponse createFeedBack(FeedbackRequest feedback, String userId, UUID movieId);
}
