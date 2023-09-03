package dev.alpey.foodfusionbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    private Long id;

    private double quantity;

    private Long foodId;

    private Long orderId;
}
