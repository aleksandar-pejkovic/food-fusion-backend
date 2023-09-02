package dev.alpey.foodfusionbackend.service.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.model.dto.CategoryDTO;
import dev.alpey.foodfusionbackend.model.entity.Category;
import dev.alpey.foodfusionbackend.repository.CategoryRepository;

@Component
public class CategoryMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    Category convertToEntity(CategoryDTO categoryDTO) {
        return mapper.map(categoryDTO, Category.class);
    }

    Category convertToExistingEntity(CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryDTO.getId()).orElseThrow();
        mapper.map(categoryDTO, existingCategory);
        return existingCategory;
    }

    CategoryDTO convertToDto(Category category) {
        return mapper.map(category, CategoryDTO.class);
    }
}
