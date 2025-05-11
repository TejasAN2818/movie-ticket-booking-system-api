package com.example.mtb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("app")
public class AppENV {

    private Token token;

    @Setter
    @Getter
    public static class Token{
        private String secret;
    }

}
