package dev.alpey.foodfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.alpey.foodfusionbackend.model.dto.FoodDTO;
import dev.alpey.foodfusionbackend.service.food.FoodService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public FoodDTO saveFood(@Valid @RequestBody FoodDTO foodDTO) {
        return foodService.saveFood(foodDTO);
    }

    @PutMapping
    public FoodDTO updateFood(@Valid @RequestBody FoodDTO foodDTO) {
        return foodService.updateFood(foodDTO);
    }

    @PutMapping("/{id}/update-image")
    public FoodDTO updateFoodImage(@RequestParam MultipartFile imageFile,
                                   @PathVariable("id") Long foodId) {
        return foodService.updateFoodImage(imageFile, foodId);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
    }

    @GetMapping("/{id}")
    public FoodDTO getFoodById(@PathVariable Long id) {
        return foodService.loadFoodById(id);
    }

    @GetMapping("/categoryId/{id}")
    public List<FoodDTO> getFoodListByCategoryId(@PathVariable Long id) {
        return foodService.loadFoodListByCategoryId(id);
    }

    @GetMapping("/current")
    public List<FoodDTO> getCurrentUserFoods() {
        return foodService.loadFoodListForCurrentUser();
    }

    @Secured("SCOPE_UNRESTRICTED")
    @GetMapping
    public List<FoodDTO> getAllFoods() {
        return foodService.loadAllFood();
    }
}
