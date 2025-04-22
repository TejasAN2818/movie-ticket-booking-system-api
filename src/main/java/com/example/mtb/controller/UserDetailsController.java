package com.example.mtb.controller;

import com.example.mtb.entity.UserDetails;
import com.example.mtb.service.UserService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@ResponseBody

public class UserDetailsController {

    private final UserService userService;

    private final RestResponseBuilder restResponseBuilder;

    @PostMapping("/user")
    public ResponseEntity<ResponseStructure<UserDetails>> registerUser(@RequestBody UserDetails user){
        UserDetails userDetails = userService.userRegister(user);

        return restResponseBuilder.sucess(HttpStatus.CREATED, "User register sucessfully", userDetails);
    }
}
