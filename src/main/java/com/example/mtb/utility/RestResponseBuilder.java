package com.example.mtb.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestResponseBuilder {

    public <T>ResponseEntity<ResponseStructure<T>> sucess(HttpStatus status, String message, T data){
        ResponseStructure<T> str=ResponseStructure
                .<T>builder()
                .statusCode(status.value())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(str, status);
    }

    public <T> ResponseEntity<ResponseStructure<T>> error(HttpStatus status, String message, T data) {
        ResponseStructure<T> response = new ResponseStructure<>();
        response.setStatusCode(status.value());
        response.setMessage(message);
        response.setData(data);
        return new ResponseEntity<>(response, status);
    }
}
