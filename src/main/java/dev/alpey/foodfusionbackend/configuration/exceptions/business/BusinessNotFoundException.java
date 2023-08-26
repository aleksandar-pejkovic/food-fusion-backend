package dev.alpey.foodfusionbackend.configuration.exceptions.business;

import jakarta.validation.constraints.NotEmpty;

public class BusinessNotFoundException extends RuntimeException {
    public BusinessNotFoundException(@NotEmpty String errorMessage) {
        super(errorMessage);
    }
}
