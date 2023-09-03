package dev.alpey.foodfusionbackend.configuration.validation.order.status;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = OrderStatusValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderStatus {

    String message() default "Invalid status! Valid status: [Neizmireno, Delimično izmireno, Plaćeno]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
