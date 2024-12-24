package com.gestion_stock.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidEntityException extends RuntimeException{
    private Integer httpCode;
    private List<String> errors;
    public InvalidEntityException(String message) {
        super(message);
    }
    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidEntityException(String message,List<String> errors) {
        super(message);
        this.errors = errors;
    }
    public InvalidEntityException(String message,List<String> errors, Integer code) {
        super(message);
        this.errors = errors;
        this.httpCode=code;
    }
    public InvalidEntityException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.httpCode = code;
    }
    public InvalidEntityException(String message,Integer code,List<String> errors){
        super(message);
        this.httpCode=code;
        this.errors=errors;
    }


}
