package dev.alpey.foodfusionbackend.model.dto;

import java.time.LocalDate;

import dev.alpey.foodfusionbackend.configuration.validation.user.name.Name;
import dev.alpey.foodfusionbackend.configuration.validation.user.password.Password;
import dev.alpey.foodfusionbackend.configuration.validation.user.username.Username;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    @Username
    private String username;

    @Password
    private String password;

    @Email
    private String email;

    @Name
    private String name;

    private LocalDate createdDate;
}
