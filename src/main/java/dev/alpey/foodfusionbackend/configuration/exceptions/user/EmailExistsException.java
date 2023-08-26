package dev.alpey.foodfusionbackend.configuration.exceptions.user;

import jakarta.validation.constraints.NotEmpty;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(@NotEmpty String errorMessage) {
        super(errorMessage);
    }
}
