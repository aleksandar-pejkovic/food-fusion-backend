package dev.alpey.foodfusionbackend.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Order;

@Repository
public interface OrderRepository extends ListCrudRepository<Order, Long> {
}
