package com.example.mtb.controller;


import com.example.mtb.dto.UserRegistrationResuest;
import com.example.mtb.dto.UserRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.service.UserService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@ResponseBody
@RequestMapping("/user")

public class UserDetailsController {

    private final UserService userService;

    private final RestResponseBuilder restResponseBuilder;

    @PostMapping
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRegistrationResuest user){
        UserResponse userResponse = userService.userRegister(user);

        return restResponseBuilder.sucess(HttpStatus.CREATED, "User register sucessfully", userResponse);
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<String>> updateUser(@RequestBody UserRequest updatedUser, String email){
        String str=userService.updateUser(updatedUser, email);

        return restResponseBuilder.sucess(HttpStatus.OK, "Update user sucessfully", str);

    }



}
