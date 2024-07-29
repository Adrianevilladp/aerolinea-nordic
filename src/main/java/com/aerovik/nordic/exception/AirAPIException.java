package com.aerovik.nordic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class AirAPIException extends RuntimeException{

    private String message;
    private HttpStatus status;


}
