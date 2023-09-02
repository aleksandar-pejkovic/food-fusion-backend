package dev.alpey.foodfusionbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private Long id;

    private String name;

    private byte[] image;
}
