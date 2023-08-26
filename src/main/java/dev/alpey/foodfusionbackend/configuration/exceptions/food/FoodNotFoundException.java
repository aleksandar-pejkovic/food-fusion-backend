package dev.alpey.foodfusionbackend.configuration.exceptions.food;

import jakarta.validation.constraints.NotEmpty;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(@NotEmpty String errorMessage) {
        super(errorMessage);
    }
}
