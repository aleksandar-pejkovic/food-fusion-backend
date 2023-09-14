package dev.alpey.foodfusionbackend.service.condiment;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.model.dto.CondimentDTO;
import dev.alpey.foodfusionbackend.model.entity.Category;
import dev.alpey.foodfusionbackend.model.entity.Condiment;
import dev.alpey.foodfusionbackend.repository.CategoryRepository;
import dev.alpey.foodfusionbackend.repository.CondimentRepository;
import dev.alpey.foodfusionbackend.utils.ImageResize;

@Component
public class CondimentMapper {

    @Autowired
    private CondimentRepository condimentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    Condiment convertToEntity(CondimentDTO condimentDTO) {
        Category category = categoryRepository.findById(condimentDTO.getCategoryId()).orElseThrow();
        Condiment condiment = mapper.map(condimentDTO, Condiment.class);
        condiment.setCategory(category);
        resizeImageIfExist(condiment);
        return condiment;
    }

    Condiment convertToExistingEntity(CondimentDTO condimentDTO) {
        Condiment existingCondiment = condimentRepository.findById(condimentDTO.getId()).orElseThrow();
        mapper.map(condimentDTO, existingCondiment);
        Category category = categoryRepository.findById(condimentDTO.getCategoryId()).orElseThrow();
        existingCondiment.setCategory(category);
        resizeImageIfExist(existingCondiment);
        return existingCondiment;
    }

    CondimentDTO convertToDto(Condiment condiment) {
        CondimentDTO condimentDTO = mapper.map(condiment, CondimentDTO.class);
        condimentDTO.setCategoryId(condiment.getCategory().getId());
        return condimentDTO;
    }

    List<CondimentDTO> convertToDtoList(List<Condiment> condimentList) {
        return condimentList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private void resizeImageIfExist(Condiment condiment) {
        if (condiment.getImage() != null) {
            byte[] resizedImage = ImageResize.resizeImage(condiment.getImage());
            condiment.setImage(resizedImage);
        }
    }
}
