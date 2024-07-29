package com.aerovik.nordic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidArgumentException extends RuntimeException{
    private String message;
}
