package dev.alpey.foodfusionbackend.configuration.exceptions.passwordResetToken;

import jakarta.validation.constraints.NotEmpty;

public class PasswordResetTokenExpiredException extends RuntimeException {
    public PasswordResetTokenExpiredException(@NotEmpty String errorMessage) {
        super(errorMessage);
    }
}
