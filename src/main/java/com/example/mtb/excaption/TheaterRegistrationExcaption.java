package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class TheaterRegistrationExcaption extends RuntimeException{

    private String message;

    public TheaterRegistrationExcaption(String message){
        this.message=message;
    }
}
