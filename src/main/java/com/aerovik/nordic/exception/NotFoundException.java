package com.aerovik.nordic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Getter
public class NotFoundException extends RuntimeException{
    private String message;

}
