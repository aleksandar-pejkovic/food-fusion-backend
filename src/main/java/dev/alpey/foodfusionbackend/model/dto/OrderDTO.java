package dev.alpey.foodfusionbackend.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    private Long id;

    private int orderNumber;

    private LocalDate creationDate;

    private String status;

    private Long userId;
}
