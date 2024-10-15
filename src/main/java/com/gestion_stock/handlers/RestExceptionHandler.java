package com.gestion_stock.handlers;

import com.gestion_stock.exception.EntityNotFoundException;
import com.gestion_stock.exception.ErrorDto;
import com.gestion_stock.exception.InvalidEntityException;
import com.gestion_stock.exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleExceptionNotFound(EntityNotFoundException exception, WebRequest webRequest){
        HttpStatus code=HttpStatus.NOT_FOUND;
        ErrorDto errorDto=ErrorDto.builder()
                .message(exception.getMessage())
                .httpStatus(code)
                .cause(exception.getCause())
                .build();
        return new ResponseEntity<>(errorDto,code);
    }

    @ExceptionHandler(value = {InvalidEntityException.class})
    public ResponseEntity<Object> handleInvalidException(InvalidEntityException exception,WebRequest webRequest){
        ErrorDto errorDto=ErrorDto.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .cause(exception)
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity(errorDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = InvalidOperationException.class)
    public ResponseEntity<Object> handleInvalidOperation(InvalidOperationException exception,WebRequest webRequest){
        ErrorDto errorDto=ErrorDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto,HttpStatus.BAD_REQUEST);
    }
}
