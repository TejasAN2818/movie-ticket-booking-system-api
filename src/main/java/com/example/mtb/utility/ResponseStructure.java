package com.example.mtb.utility;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure<T> {

    private int statusCode;
    private String message;
    private T data;
}
