package com.example.mtb.excaption.handler;

import com.example.mtb.utility.FieldErrorStructure;
import com.example.mtb.utility.RestFieldErrorBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
@RestControllerAdvice
@AllArgsConstructor
public class FieldErrorExcaptionHandler extends ResponseEntityExceptionHandler {

    private final RestFieldErrorBuilder restFieldErrorBuilder;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<CustomFieldsError> customFieldErrors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            CustomFieldsError cfe=new CustomFieldsError(
                    fieldError.getField(),
                    fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : null,
                    fieldError.getDefaultMessage()
            );
            customFieldErrors.add(cfe);
        }

        ResponseEntity<FieldErrorStructure<List<CustomFieldsError>>> response =
                restFieldErrorBuilder.failfieldError(HttpStatus.BAD_REQUEST, "Invalid Input", customFieldErrors);


        return (ResponseEntity) response;



    }

    @Getter
    public static class CustomFieldsError{
        String field;
        String rejectedValue;
        String errorMessage;

        public CustomFieldsError(String field, String rejectedValue, String errorMessage){
            this.field=field;
            this.rejectedValue=rejectedValue;
            this.errorMessage=errorMessage;
        }
    }
}
