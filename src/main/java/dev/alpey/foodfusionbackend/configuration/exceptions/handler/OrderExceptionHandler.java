package dev.alpey.foodfusionbackend.configuration.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.alpey.foodfusionbackend.configuration.exceptions.order.OrderNotFoundException;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public String handleInvoiceNotFoundException(OrderNotFoundException ex) {
        return ex.getMessage();
    }
}
