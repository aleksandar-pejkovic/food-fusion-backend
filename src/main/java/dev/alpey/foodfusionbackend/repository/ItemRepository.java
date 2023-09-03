package dev.alpey.foodfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Item;
import dev.alpey.foodfusionbackend.model.entity.Order;

@Repository
public interface ItemRepository extends ListCrudRepository<Item, Long> {

    @Query("SELECT * FROM Item i WHERE i.order.id = :orderId")
    List<Item> findByOrderId(@Param("orderId") Long orderId);
}
