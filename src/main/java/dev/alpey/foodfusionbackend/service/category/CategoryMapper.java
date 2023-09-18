package dev.alpey.foodfusionbackend.service.category;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.model.dto.CategoryDTO;
import dev.alpey.foodfusionbackend.model.entity.Business;
import dev.alpey.foodfusionbackend.model.entity.Category;
import dev.alpey.foodfusionbackend.repository.BusinessRepository;
import dev.alpey.foodfusionbackend.repository.CategoryRepository;

@Component
public class CategoryMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ModelMapper mapper;

    Category convertToEntity(CategoryDTO categoryDTO) {
        Business business = businessRepository.findById(categoryDTO.getBusinessId()).orElseThrow();
        Category category = mapper.map(categoryDTO, Category.class);
        category.setBusiness(business);
        return category;
    }

    Category convertToExistingEntity(CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryDTO.getId()).orElseThrow();
        mapper.map(categoryDTO, existingCategory);
        return existingCategory;
    }

    CategoryDTO convertToDto(Category category) {
        CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);
        categoryDTO.setBusinessId(category.getBusiness().getId());
        return categoryDTO;
    }

    List<CategoryDTO> convertToDtoList(List<Category> categoryList) {
        return categoryList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
