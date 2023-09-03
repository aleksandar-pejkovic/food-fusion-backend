package dev.alpey.foodfusionbackend.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.alpey.foodfusionbackend.model.entity.Condiment;

@Repository
public interface CondimentRepository extends ListCrudRepository<Condiment, Long> {
}
