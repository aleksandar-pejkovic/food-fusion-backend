package dev.alpey.foodfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Condiment;

@Repository
public interface CondimentRepository extends ListCrudRepository<Condiment, Long> {

    @Query("SELECT c FROM Condiment c JOIN Food f ON c.category.id = f.category.id WHERE f.id = :foodId")
    List<Condiment> findByFoodId(@Param("foodId") Long foodId);

    @Query("SELECT c FROM Condiment c JOIN c.itemList i WHERE i.id = :itemId")
    List<Condiment> findByItemId(@Param("itemId") Long itemId);
}
