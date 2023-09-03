package dev.alpey.foodfusionbackend.service.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.model.dto.BusinessDTO;
import dev.alpey.foodfusionbackend.model.dto.CategoryDTO;
import dev.alpey.foodfusionbackend.model.entity.Category;
import dev.alpey.foodfusionbackend.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryMapper mapper;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = mapper.convertToEntity(categoryDTO);
        Category savedCategory = repository.save(category);
        return mapper.convertToDto(savedCategory);
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category existingCategory = mapper.convertToExistingEntity(categoryDTO);
        Category updatedCategory = repository.save(existingCategory);
        return mapper.convertToDto(updatedCategory);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    public CategoryDTO loadCategoryById(Long id) {
        Category category = repository.findById(id).orElseThrow();
        return mapper.convertToDto(category);
    }

    public List<CategoryDTO> loadCategoryListForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsername(username)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> loadAllCategories() {
        return repository.findAll()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }
}
