package com.example.mtb.service.impl;

import com.example.mtb.dto.auth_dto.AuthResponse;
import com.example.mtb.dto.auth_dto.LoginRequest;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.excaption.UserNotFoundByEmailExcaption;
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
                Instant.now().plus(Duration.ofHours(1))
        ));

    }

    @Override
    public AuthResponse refreshToken(LoginRequest loginRequest) {

//        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        // Authenticate again (optional based on your strategy)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        UserDetails userDetails = userDetailsRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new UserNotFoundByEmailExcaption("User not found by Email"));

        // Generate access and refresh tokens
        Instant now=Instant.now();
        Instant accessExp=now.plus(Duration.ofMinutes(5));
        Instant refershExp=now.plus(Duration.ofDays(1));

        String accessToken = jwtService.createJwtToken(new TokenPayload(
                Map.of(),
                userDetails.getEmail(),
                now,
                accessExp
        ));

        String refreshToken = jwtService.createJwtToken(new TokenPayload(
                Map.of(),
                userDetails.getEmail(),
                now,
                refershExp
        ));



        return new AuthResponse(
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getUserRole(),
                accessExp,
                refershExp,
                accessToken,
                refreshToken
        );
    }
}
