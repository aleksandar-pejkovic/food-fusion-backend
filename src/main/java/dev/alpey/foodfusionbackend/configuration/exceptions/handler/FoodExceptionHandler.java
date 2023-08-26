package dev.alpey.foodfusionbackend.configuration.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.alpey.foodfusionbackend.configuration.exceptions.food.FoodNotFoundException;

@RestControllerAdvice
public class FoodExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FoodNotFoundException.class)
    public String handleProductNotFoundException(FoodNotFoundException ex) {
        return ex.getMessage();
    }
}
