package dev.alpey.foodfusionbackend.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Category;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Long> {
}
