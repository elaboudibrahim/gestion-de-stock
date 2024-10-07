package com.gestion_stock.exception;

import lombok.Getter;
@Getter

public class InvalidEntityException extends RuntimeException{
    private Integer httpCode;
    public InvalidEntityException(String message) {
        super(message);
    }
    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidEntityException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.httpCode = code;
    }
    public InvalidEntityException(String message,Integer code){
        super(message);
        this.httpCode=code;
    }


}
