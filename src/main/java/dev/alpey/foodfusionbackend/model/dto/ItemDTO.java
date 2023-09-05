package dev.alpey.foodfusionbackend.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    private Long id;

    private double quantity;

    private Long foodId;

    private Long orderId;

    private List<Long> condimentIdList = new ArrayList<>();
}
