package com.example.mtb.utility;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorStructure<T> {
    private int errorCode;
    private String message;
    private T error;
}
