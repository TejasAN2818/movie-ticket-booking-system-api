package com.example.mtb.controller;


import com.example.mtb.dto.UserRegistrationResuest;
import com.example.mtb.dto.UserRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.service.UserService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@ResponseBody
@RequestMapping

public class UserDetailsController {

    private final UserService userService;

    private final RestResponseBuilder restResponseBuilder;








    @PostMapping("/user")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody @Valid UserRegistrationResuest user){
        UserResponse userResponse = userService.userRegister(user);

        return restResponseBuilder.sucess(HttpStatus.CREATED, "User register sucessfully", userResponse);
    }

    @PutMapping("/user")
    public ResponseEntity<ResponseStructure<String>> updateUser(@RequestBody @Valid UserRequest updatedUser, @RequestParam String email){
        String str=userService.updateUser(updatedUser, email);

        return restResponseBuilder.sucess(HttpStatus.OK, "Update user sucessfully", str);

    }

    @DeleteMapping("/user")
    public ResponseEntity<ResponseStructure<String>> softDelete(@RequestParam String email){
        String str=userService.softDelete(email);
        //doneeeeeee
        return restResponseBuilder.sucess(HttpStatus.OK, "User deleted sucessfully", str);

    }



}
