package com.example.mtb.controller;

import com.example.mtb.dto.auth_dto.AuthResponse;
import com.example.mtb.dto.auth_dto.LoginRequest;
import com.example.mtb.service.AuthServices;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@ResponseBody
@AllArgsConstructor
public class AuthController {

    private final AuthServices authServices;
    private RestResponseBuilder restResponseBuilder;




    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        String jwttokens=authServices.login(loginRequest);
        return ResponseEntity.ok(jwttokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseStructure<AuthResponse>> refreshToken(@RequestBody LoginRequest loginRequest){

        AuthResponse authResponse=authServices.refreshToken(loginRequest);

        return restResponseBuilder.sucess(HttpStatus.OK, "token refreshed sucessfully", authResponse);

    }

}
