package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class UserNotFoundByIdExcaption extends RuntimeException{

    private String message;

    public UserNotFoundByIdExcaption(String message){
        this.message=message;
    }
}
