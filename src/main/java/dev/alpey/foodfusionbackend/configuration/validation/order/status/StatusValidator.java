package dev.alpey.foodfusionbackend.configuration.validation.order.status;

import dev.alpey.foodfusionbackend.enums.OrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<Status, String> {

    @Override
    public void initialize(Status orderStatus) {
    }

    @Override
    public boolean isValid(String paymentStatus, ConstraintValidatorContext context) {
        for (OrderStatus value : dev.alpey.foodfusionbackend.enums.OrderStatus.values()) {
            if (value.getStatus().equalsIgnoreCase(paymentStatus)) {
                return true;
            }
        }
        return false;
    }
}
