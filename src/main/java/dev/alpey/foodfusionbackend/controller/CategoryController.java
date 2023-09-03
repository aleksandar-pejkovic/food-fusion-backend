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
import org.springframework.web.bind.annotation.RestController;

import dev.alpey.foodfusionbackend.model.dto.CategoryDTO;
import dev.alpey.foodfusionbackend.service.category.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok(savedCategoryDTO);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO);
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.loadCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/current")
    public ResponseEntity<List<CategoryDTO>> getCurrentUserCategories() {
        List<CategoryDTO> categoryDTOList = categoryService.loadCategoryListForCurrentUser();
        return ResponseEntity.ok(categoryDTOList);
    }

    @Secured("SCOPE_UNRESTRICTED")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOList = categoryService.loadAllCategories();
        return ResponseEntity.ok(categoryDTOList);
    }
}
