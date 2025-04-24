package com.example.mtb.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestFieldErrorBuilder {

    public <T>ResponseEntity<FieldErrorStructure<T>> failfieldError(HttpStatus status, String message, T errors){
        FieldErrorStructure<T> fieldErrorStructure= FieldErrorStructure
                .<T>builder()
                .status(status.value())
                .message(message)
                .errors(errors)
                .build();
        return new ResponseEntity<>(fieldErrorStructure, status);
    }

}