package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class ShowTimeNotAvailableExcaption extends RuntimeException{

    private String message;

    public ShowTimeNotAvailableExcaption(String message){
        this.message=message;
    }
}
