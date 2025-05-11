package com.example.mtb.controller;

import com.example.mtb.dto.seat_dto.SeatResponse;
import com.example.mtb.entity.Seat;
import com.example.mtb.service.SeatServices;
import com.example.mtb.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping
@ResponseBody
public class SeatController {

}
