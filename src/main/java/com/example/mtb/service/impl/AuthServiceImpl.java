package com.example.mtb.service.impl;

import com.example.mtb.dto.auth_dto.LoginRequest;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.security.JwtService;
import com.example.mtb.security.TokenPayload;
import com.example.mtb.service.AuthServices;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthServices {

    private final UserDetailsRepository userDetailsRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public String login(LoginRequest loginRequest) {


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

        final UserDetails userDetails = userDetailsRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jwtService.createJwtToken(new TokenPayload(
                Map.of(),
                userDetails.getEmail(),
                Instant.now(),
                Instant.now().plus(Duration.ofHours(10))
        ));

    }
}
