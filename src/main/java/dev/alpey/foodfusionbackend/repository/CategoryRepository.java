package dev.alpey.foodfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Category;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.user.username = :username")
    List<Category> findByUsername(@Param("username") String username);

    @Query("SELECT c FROM Category c "
            + "JOIN Business b "
            + "ON c.user.id = b.user.id "
            + "WHERE b.name = :businessName")
    List<Category> findByBusinessName(@Param("businessName") String businessName);
}
