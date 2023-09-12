package dev.alpey.foodfusionbackend.service.food;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.model.dto.FoodDTO;
import dev.alpey.foodfusionbackend.model.entity.Category;
import dev.alpey.foodfusionbackend.model.entity.Food;
import dev.alpey.foodfusionbackend.repository.CategoryRepository;
import dev.alpey.foodfusionbackend.repository.FoodRepository;
import dev.alpey.foodfusionbackend.repository.UserRepository;
import dev.alpey.foodfusionbackend.utils.ImageResize;

@Component
public class FoodMapper {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    Food convertToEntity(FoodDTO foodDTO) {
        Category category = categoryRepository.findById(foodDTO.getCategoryId()).orElseThrow();
        Food food = mapper.map(foodDTO, Food.class);
        food.setCategory(category);
        resizeImageIfExist(food);
        return food;
    }

    Food convertToExistingEntity(FoodDTO foodDTO) {
        Food existingFood = foodRepository.findById(foodDTO.getCategoryId()).orElseThrow();
        mapper.map(foodDTO, existingFood);
        resizeImageIfExist(existingFood);
        return existingFood;
    }

    FoodDTO convertToDto(Food food) {
        FoodDTO foodDTO = mapper.map(food, FoodDTO.class);
        foodDTO.setCategoryId(food.getCategory().getId());
        return foodDTO;
    }

    List<FoodDTO> convertToDtoList(List<Food> foodList) {
        return foodList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private void resizeImageIfExist(Food food) {
        if (food.getImage() != null) {
            byte[] resizedImage = ImageResize.resizeImage(food.getImage());
            food.setImage(resizedImage);
        }
    }
}
