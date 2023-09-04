package dev.alpey.foodfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Condiment;

@Repository
public interface CondimentRepository extends ListCrudRepository<Condiment, Long> {

    @Query("SELECT c FROM Condiment c WHERE c.category.id = :categoryId")
    List<Condiment> findByCategoryId(@Param("categoryId") Long categoryId);
}
