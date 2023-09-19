package dev.alpey.foodfusionbackend.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToMany(mappedBy = "itemList")
    private List<Condiment> condimentList = new ArrayList<>();
}
