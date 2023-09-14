package dev.alpey.foodfusionbackend.service.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.alpey.foodfusionbackend.model.dto.FoodDTO;
import dev.alpey.foodfusionbackend.model.entity.Food;
import dev.alpey.foodfusionbackend.repository.FoodRepository;
import dev.alpey.foodfusionbackend.utils.ImageResize;

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

    public byte[] updateFoodImage(MultipartFile imageFile, Long foodId) {
        Food existingFood = repository.findById(foodId).orElseThrow();
        byte[] resizedImage = ImageResize.resizeImage(imageFile);
        existingFood.setImage(resizedImage);
        return repository.save(existingFood).getImage();
    }

    public void deleteFood(Long id) {
        repository.deleteById(id);
    }

    public FoodDTO loadFoodById(Long id) {
        Food food = repository.findById(id).orElseThrow();
        return mapper.convertToDto(food);
    }

    public List<FoodDTO> loadFoodListByCategoryId(Long categoryId) {
        return mapper.convertToDtoList(repository.findByCategoryId(categoryId));
    }

    public List<FoodDTO> loadFoodListForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return mapper.convertToDtoList(repository.findByUsername(username));
    }

    public List<FoodDTO> loadAllFood() {
        return mapper.convertToDtoList(repository.findAll());
    }
}
