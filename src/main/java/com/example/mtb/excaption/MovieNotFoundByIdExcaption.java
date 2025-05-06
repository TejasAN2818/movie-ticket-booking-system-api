package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class MovieNotFoundByIdExcaption extends RuntimeException{

    private String message;

    public MovieNotFoundByIdExcaption(String message){
        super();
        this.message=message;
    }
}
