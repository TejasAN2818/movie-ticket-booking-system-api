package com.example.mtb.excaption.handler;

import com.example.mtb.excaption.*;
import com.example.mtb.utility.ErrorStructure;
import com.example.mtb.utility.RestErrorBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.IllegalArgumentException;

@AllArgsConstructor
@RestControllerAdvice
public class UserExceptionHandler {

    private final RestErrorBuilder restErrorBuilder;


    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerUserRegistrationexcaption(UserRegistrationexcaption ex){

        return restErrorBuilder.fail(HttpStatus.NOT_FOUND, ex.getMessage(), "this email already present try another one");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerIllegalArgumentException(IllegalArgumentException ex){

        return restErrorBuilder.fail(HttpStatus.BAD_REQUEST, ex.getMessage(), "enter the proper UserRole");

    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerUserNotFoundByEmailExcaption(UserNotFoundByEmailExcaption ex){

        return restErrorBuilder.fail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Enter the proper email");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerTheaterRegistrationExcaption(TheaterRegistrationExcaption ex){
        return restErrorBuilder.fail(HttpStatus.NOT_FOUND, ex.getMessage(), "this user is not theater owner");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerTheaterNotFoundByIdExcaption(TheaterNotFoundByIdExcaption ex){
        return restErrorBuilder.fail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Theater not found by this id");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerScreenNotFoundByIdExcaption(ScreenNotFoundByIdExcaption ex){
        return restErrorBuilder.fail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Screen not found by id");
    }

}
