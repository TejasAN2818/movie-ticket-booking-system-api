package com.example.mtb.utility;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseStructure<T> {

    private int statusCode;
    private String message;
    private T data;
}
