package com.main.productservice.exceptions;

import com.main.productservice.dtos.ExceptionData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices { // Act like Interceptors ( Controller->ControllerAdvice->DispatcherServlet)
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionData> handleNotFoundException(NotFoundException e){// Spring Boot will automatically call when any method in controller will throw this exception
        return new ResponseEntity<>(new ExceptionData(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()),HttpStatus.NOT_FOUND);
    }
}
