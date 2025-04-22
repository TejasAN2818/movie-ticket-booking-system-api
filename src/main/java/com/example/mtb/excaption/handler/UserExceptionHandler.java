package com.example.mtb.excaption.handler;

import com.example.mtb.excaption.UserRegistrationexcaption;
import com.example.mtb.utility.ErrorStructure;
import com.example.mtb.utility.RestErrorBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

}
