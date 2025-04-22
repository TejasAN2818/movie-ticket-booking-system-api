package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class IllegalArgumentException extends RuntimeException{

    private String message;

    public IllegalArgumentException(String mesage){
        super();
        this.message=message;
    }


}
