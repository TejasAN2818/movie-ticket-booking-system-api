package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class UserNotFoundByEmailExcaption extends RuntimeException{

    private String message;

    public UserNotFoundByEmailExcaption(String message){
        super();
        this.message=message;
    }
}