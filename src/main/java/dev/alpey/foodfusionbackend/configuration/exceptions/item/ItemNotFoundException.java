package dev.alpey.foodfusionbackend.configuration.exceptions.item;

import jakarta.validation.constraints.NotEmpty;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(@NotEmpty String errorMessage) {
        super(errorMessage);
    }
}
