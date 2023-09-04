package dev.alpey.foodfusionbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondimentDTO {

    private Long id;

    private String name;

    private String description;

    private Long categoryId;
}
