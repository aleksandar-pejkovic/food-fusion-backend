package dev.alpey.foodfusionbackend.service.food;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.model.dto.FoodDTO;
import dev.alpey.foodfusionbackend.model.entity.Food;
import dev.alpey.foodfusionbackend.repository.FoodRepository;

@Service
public class FoodService {

    @Autowired
    private FoodRepository repository;

    @Autowired
    private FoodMapper mapper;

    public FoodDTO saveFood(FoodDTO foodDTO) {
        Food food = mapper.convertToEntity(foodDTO);
        Food savedFood = repository.save(food);
        return mapper.convertToDto(savedFood);
    }

    public FoodDTO updateFood(FoodDTO foodDTO) {
        Food existingFood = mapper.convertToExistingEntity(foodDTO);
        Food updatedFood = repository.save(existingFood);
        return mapper.convertToDto(updatedFood);
    }

    public void deleteFood(Long id) {
        repository.deleteById(id);
    }

    public FoodDTO loadFoodById(Long id) {
        Food food = repository.findById(id).orElseThrow();
        return mapper.convertToDto(food);
    }

    public List<FoodDTO> loadAllFood() {
        return repository.findAll()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }
}
