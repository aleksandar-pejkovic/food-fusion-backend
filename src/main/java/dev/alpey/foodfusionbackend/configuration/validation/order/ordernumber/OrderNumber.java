package dev.alpey.foodfusionbackend.configuration.validation.order.ordernumber;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = OrderNumberValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderNumber {

    String message() default "Invalid invoice number format!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
