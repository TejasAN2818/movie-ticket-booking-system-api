package com.example.mtb.controller;

import com.example.mtb.dto.auth_dto.LoginRequest;
import com.example.mtb.service.AuthServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@ResponseBody
@AllArgsConstructor
public class AuthController {

    private final AuthServices authServices;





    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        String jwttokens=authServices.login(loginRequest);
        return ResponseEntity.ok(jwttokens);
    }

}
