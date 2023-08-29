package dev.alpey.foodfusionbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessDTO {

    private Long id;

    private String name;

    private String director;

    private String registrationNumber;

    private String taxNumber;

    private String bankAccount;

    private String street;

    private String zip;

    private String city;

    private String phone;

    private String email;

    private String website;

    private Long userId;
}
