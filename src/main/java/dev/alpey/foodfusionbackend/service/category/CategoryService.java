package dev.alpey.foodfusionbackend.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.alpey.foodfusionbackend.model.dto.CategoryDTO;
import dev.alpey.foodfusionbackend.model.entity.Category;
import dev.alpey.foodfusionbackend.repository.CategoryRepository;
import dev.alpey.foodfusionbackend.utils.ImageResize;

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

    public CategoryDTO updateCategoryImage(MultipartFile imageFile, Long categoryId) {
        Category existingCategory = repository.findById(categoryId).orElseThrow();
        byte[] resizedImage = ImageResize.resizeImage(imageFile);
        existingCategory.setImage(resizedImage);
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

    public List<CategoryDTO> loadCategoryListByBusinessId(Long businessId) {
        return mapper.convertToDtoList(repository.findByBusinessId(businessId));
    }

    public List<CategoryDTO> loadAllCategories() {
        return mapper.convertToDtoList(repository.findAll());
    }
}
