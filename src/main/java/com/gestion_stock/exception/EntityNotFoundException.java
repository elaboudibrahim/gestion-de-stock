package com.gestion_stock.exception;

public class EntityNotFoundException extends RuntimeException{
    private Integer httpCode;
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
    public EntityNotFoundException(String message,Integer code){
        super(message);
        this.httpCode=code;
    }
}
