package dev.alpey.foodfusionbackend.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String name;

    private LocalDate createdDate;

    private Long businessId;
}
