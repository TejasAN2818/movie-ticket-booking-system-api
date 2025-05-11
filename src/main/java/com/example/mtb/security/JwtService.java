package com.example.mtb.security;

import com.example.mtb.config.AppENV;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@Component
@AllArgsConstructor
public class JwtService {

    //private String secret;
    private final AppENV env;

    public String createJwtToken(TokenPayload tokenPayload) {
        return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setSubject(tokenPayload.subject())
                .setIssuedAt(new Date(tokenPayload.issuedAt().toEpochMilli()))
                .setExpiration(new Date(tokenPayload.expiration().toEpochMilli()))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignatureKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getToken().getSecret()));
    }

    public Claims pasresToken(String token){
        return jwts.parserBuilder().setSigninkey(getSignatureKey()).build().parseClaims
    }


}

