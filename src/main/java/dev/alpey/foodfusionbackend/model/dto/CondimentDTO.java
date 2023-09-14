package dev.alpey.foodfusionbackend.model.dto;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondimentDTO {

    private Long id;

    private String name;

    private String description;

    @Lob
    private byte[] image;

    private Long categoryId;
}
