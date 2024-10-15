package com.gestion_stock.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class InvalidOperationException extends RuntimeException {
    Integer httpCode;
    List<String> errors;
    public InvalidOperationException(String s) {
        super(s);
    }
    public InvalidOperationException(String message, Integer code, List<String> errors){
        super(message);
        this.httpCode=code;
        this.errors=errors;
    }
}
