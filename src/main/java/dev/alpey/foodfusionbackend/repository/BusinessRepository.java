package dev.alpey.foodfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Business;

@Repository
public interface BusinessRepository extends ListCrudRepository<Business, Long> {

    @Query("SELECT * FROM Business b WHERE b.user.username = :username")
    List<Business> findByUsername(@Param("username") String username);
}
