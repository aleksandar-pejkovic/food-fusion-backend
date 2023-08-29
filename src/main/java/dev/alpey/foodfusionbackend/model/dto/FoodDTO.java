package dev.alpey.foodfusionbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {

    private Long id;

    private String name;

    private double price;

    private byte[] image;

    private Long categoryId;
}
