package com.example.mtb.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestErrorBuilder {

    public <T> ResponseEntity<ErrorStructure<T>> fail(HttpStatus code, String message, T error) {
        ErrorStructure<T> er = ErrorStructure
                .<T>builder()
                .errorCode(code.value())
                .message(message)
                .error(error)
                .build();

        return new ResponseEntity<>(er, code);
    }
}
