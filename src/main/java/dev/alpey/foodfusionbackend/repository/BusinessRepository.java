package dev.alpey.foodfusionbackend.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Business;

@Repository
public interface BusinessRepository extends ListCrudRepository<Business, Long> {
}
