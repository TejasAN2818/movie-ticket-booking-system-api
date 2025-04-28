package com.example.mtb.excaption;

import lombok.Getter;

@Getter
public class ScreenNotFoundByIdExcaption extends RuntimeException {

    private String message;

    public ScreenNotFoundByIdExcaption(String message){
        this.message=message;
    }
}
