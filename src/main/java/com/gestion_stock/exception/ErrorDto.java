package com.gestion_stock.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDto {
    String message;
    HttpStatus httpStatus;
    Throwable cause;
}
