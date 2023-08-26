package dev.alpey.foodfusionbackend.configuration.validation.business.website;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = WebsiteValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Website {

    String message() default "Invalid format!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
