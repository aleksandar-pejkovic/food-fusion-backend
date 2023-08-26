package dev.alpey.foodfusionbackend.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Food;

@Repository
public interface FoodRepository extends ListCrudRepository<Food, Long> {
}
