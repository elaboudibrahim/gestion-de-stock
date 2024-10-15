package com.gestion_stock.exception;

import java.util.List;

public class EntityNotFoundException extends RuntimeException{
    private Integer httpCode;
    private List<String> errors;
    public EntityNotFoundException(String message) {
        super(message);
    }
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public EntityNotFoundException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.httpCode = code;
    }
    public EntityNotFoundException(String message,Integer code,List<String> errors){
        super(message);
        this.httpCode=code;
        this.errors=errors;
    }
}
