package dev.alpey.foodfusionbackend.service.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.model.dto.CategoryDTO;
import dev.alpey.foodfusionbackend.model.entity.Category;
import dev.alpey.foodfusionbackend.model.entity.User;
import dev.alpey.foodfusionbackend.repository.CategoryRepository;
import dev.alpey.foodfusionbackend.repository.UserRepository;

@Component
public class CategoryMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    Category convertToEntity(CategoryDTO categoryDTO) {
        User user = userRepository.findById(categoryDTO.getUserId()).orElseThrow();
        Category category = mapper.map(categoryDTO, Category.class);
        category.setUser(user);
        return category;
    }

    Category convertToExistingEntity(CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryDTO.getId()).orElseThrow();
        mapper.map(categoryDTO, existingCategory);
        return existingCategory;
    }

    CategoryDTO convertToDto(Category category) {
        CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);
        categoryDTO.setUserId(category.getUser().getId());
        return categoryDTO;
    }
}
