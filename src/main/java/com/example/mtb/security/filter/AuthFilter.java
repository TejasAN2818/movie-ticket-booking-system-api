package com.example.mtb.security.filter;

import com.example.mtb.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static jdk.internal.org.jline.utils.Colors.s;
import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isValid(token)){
            if (token.contains("bearer"))
                token=token.substring(7);
            var claims = jwtService.pasresToken(token);

        }
        if (isValid(email) && isValid(role) && SecurityContextHolder.getContext().getAuthentication()=)



      //  private static boolean isValid(String s){


        }





    }
}
