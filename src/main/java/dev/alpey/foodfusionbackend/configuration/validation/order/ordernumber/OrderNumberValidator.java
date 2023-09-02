package dev.alpey.foodfusionbackend.configuration.validation.order.ordernumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderNumberValidator implements ConstraintValidator<OrderNumber, String> {

    @Override
    public void initialize(OrderNumber orderNumber) {
    }

    @Override
    public boolean isValid(String invoiceNumber, ConstraintValidatorContext context) {
        String regex = "^[a-zA-Z0-9/-]+${2,30}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(invoiceNumber);
        return matcher.find();
    }
}
