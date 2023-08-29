package dev.alpey.foodfusionbackend.model.dto;

import java.time.LocalDate;

public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String name;

    private LocalDate createdDate;

    private Long businessId;
}
