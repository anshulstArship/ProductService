package com.main.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Can use instead of Controller Advices
public class NotFoundException extends Exception{
    public NotFoundException(String message){
        super(message);
    }
}
