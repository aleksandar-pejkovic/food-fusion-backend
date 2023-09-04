package dev.alpey.foodfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Food;

@Repository
public interface FoodRepository extends ListCrudRepository<Food, Long> {

    @Query("SELECT f FROM Food f WHERE f.category.user.username = :username")
    List<Food> findByUsername(@Param("username") String username);

    @Query("SELECT f FROM Food f WHERE f.category.id = :categoryId")
    List<Food> findByCategoryId(@Param("categoryId") Long categoryId);
}
