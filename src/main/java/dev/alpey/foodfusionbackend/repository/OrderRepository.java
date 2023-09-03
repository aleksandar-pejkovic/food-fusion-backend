package dev.alpey.foodfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Order;

@Repository
public interface OrderRepository extends ListCrudRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user.username = :username")
    List<Order> findByUsername(@Param("username") String username);
}
