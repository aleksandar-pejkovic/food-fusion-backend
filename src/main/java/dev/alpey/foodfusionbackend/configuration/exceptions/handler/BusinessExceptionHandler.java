package dev.alpey.foodfusionbackend.configuration.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.alpey.foodfusionbackend.configuration.exceptions.business.BusinessNotFoundException;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BusinessNotFoundException.class)
    public String handleCompanyNotFoundException(BusinessNotFoundException ex) {
        return ex.getMessage();
    }
}
