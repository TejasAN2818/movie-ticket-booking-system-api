package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class UserRegistrationexcaption extends RuntimeException{

    private String message;

    public UserRegistrationexcaption(String message){
        super();
        this.message=message;
    }


}
