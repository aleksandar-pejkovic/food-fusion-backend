package dev.alpey.foodfusionbackend.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "condiments")
@Getter
@Setter
public class Condiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Lob
    private byte[] image;

    @ManyToMany
    @JoinTable(
            name = "items_condiments",
            joinColumns = @JoinColumn(
                    name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "condiment_id", referencedColumnName = "id"))
    private List<Item> itemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
