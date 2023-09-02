package dev.alpey.foodfusionbackend.configuration.validation.order.status;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PaymentStatusValidator implements ConstraintValidator<PaymentStatus, String> {

    @Override
    public void initialize(PaymentStatus paymentStatus) {
    }

    @Override
    public boolean isValid(String paymentStatus, ConstraintValidatorContext context) {
        return "PENDING".equals(paymentStatus);
    }
}
