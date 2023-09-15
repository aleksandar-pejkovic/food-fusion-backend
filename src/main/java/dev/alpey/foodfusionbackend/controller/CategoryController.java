package dev.alpey.foodfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

import dev.alpey.foodfusionbackend.model.dto.CategoryDTO;
import dev.alpey.foodfusionbackend.service.category.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @PutMapping
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryDTO);
    }

    @PutMapping("/{id}/update-image")
    public CategoryDTO updateCategoryImage(@RequestParam MultipartFile imageFile,
                                           @PathVariable("id") Long categoryId) {
        return categoryService.updateCategoryImage(imageFile, categoryId);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return categoryService.loadCategoryById(id);
    }

    @GetMapping("/current")
    public List<CategoryDTO> getCurrentUserCategories() {
        return categoryService.loadCategoryListForCurrentUser();
    }

    @GetMapping("/business-name/{businessName}")
    public List<CategoryDTO> getCategoriesByBusinessName(@PathVariable("businessName") String businessName) {
        return categoryService.loadCategoryListByBusinessName(businessName);
    }

    @Secured("SCOPE_UNRESTRICTED")
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.loadAllCategories();
    }
}
