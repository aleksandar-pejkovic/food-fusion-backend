package dev.alpey.foodfusionbackend.configuration.validation.order.status;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderStatusValidator implements ConstraintValidator<OrderStatus, String> {

    @Override
    public void initialize(OrderStatus orderStatus) {
    }

    @Override
    public boolean isValid(String paymentStatus, ConstraintValidatorContext context) {
        for (dev.alpey.foodfusionbackend.enums.OrderStatus value : dev.alpey.foodfusionbackend.enums.OrderStatus.values()) {
            if (value.getStatus().equalsIgnoreCase(paymentStatus)) {
                return true;
            }
        }
        return false;
    }
}
