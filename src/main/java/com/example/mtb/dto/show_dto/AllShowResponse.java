package com.example.mtb.dto.show_dto;

import com.example.mtb.entity.Show;

import java.util.List;

public record AllShowResponse(
        List<ShowListResponse> showList
) {
}
