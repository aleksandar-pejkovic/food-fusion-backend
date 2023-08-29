package dev.alpey.foodfusionbackend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "business")
@Getter
@Setter
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
