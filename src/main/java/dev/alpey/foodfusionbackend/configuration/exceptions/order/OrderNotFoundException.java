package dev.alpey.foodfusionbackend.configuration.exceptions.order;

import jakarta.validation.constraints.NotEmpty;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(@NotEmpty String errorMessage) {
        super(errorMessage);
    }
}
