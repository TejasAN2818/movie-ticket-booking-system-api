package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class TheaterNotFoundByIdExcaption extends RuntimeException{

    private String message;

    public TheaterNotFoundByIdExcaption(String message){
        this.message=message;
    }
}
