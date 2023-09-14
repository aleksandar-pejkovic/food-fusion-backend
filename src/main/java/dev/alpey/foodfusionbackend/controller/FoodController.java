package dev.alpey.foodfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FoodDTO> saveFood(@Valid @RequestBody FoodDTO foodDTO) {
        FoodDTO savedFoodDTO = foodService.saveFood(foodDTO);
        return ResponseEntity.ok(savedFoodDTO);
    }

    @PutMapping
    public ResponseEntity<FoodDTO> updateFood(@Valid @RequestBody FoodDTO foodDTO) {
        FoodDTO updatedFoodDTO = foodService.updateFood(foodDTO);
        return ResponseEntity.ok(updatedFoodDTO);
    }

    @PutMapping("/{id}/update-image")
    public FoodDTO updateFoodImage(@RequestParam MultipartFile imageFile,
                                      @PathVariable("id") Long foodId) {
        return foodService.updateFoodImage(imageFile, foodId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable Long id) {
        FoodDTO foodDTO = foodService.loadFoodById(id);
        return ResponseEntity.ok(foodDTO);
    }

    @GetMapping("/categoryId/{id}")
    public ResponseEntity<List<FoodDTO>> getFoodListByCategoryId(@PathVariable Long id) {
        List<FoodDTO> foodDTOList = foodService.loadFoodListByCategoryId(id);
        return ResponseEntity.ok(foodDTOList);
    }

    @GetMapping("/current")
    public ResponseEntity<List<FoodDTO>> getCurrentUserFoods() {
        List<FoodDTO> foodDTOList = foodService.loadFoodListForCurrentUser();
        return ResponseEntity.ok(foodDTOList);
    }

    @Secured("SCOPE_UNRESTRICTED")
    @GetMapping
    public ResponseEntity<List<FoodDTO>> getAllFoods() {
        List<FoodDTO> foodDTOList = foodService.loadAllFood();
        return ResponseEntity.ok(foodDTOList);
    }
}
