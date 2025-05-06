package com.example.mtb.dto.feedback_dto;

public record FeedbackRequest(
        int ratings,
        String review
) {
}
