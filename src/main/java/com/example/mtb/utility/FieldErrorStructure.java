package com.example.mtb.utility;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FieldErrorStructure<T> {

    private int status;
    private String message;
    private T errors;
}