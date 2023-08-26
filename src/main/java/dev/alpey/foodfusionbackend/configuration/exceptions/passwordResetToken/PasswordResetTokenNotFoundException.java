package dev.alpey.foodfusionbackend.configuration.exceptions.passwordResetToken;

import jakarta.validation.constraints.NotEmpty;

public class PasswordResetTokenNotFoundException extends RuntimeException {
    public PasswordResetTokenNotFoundException(@NotEmpty String errorMessage) {
        super(errorMessage);
    }
}
