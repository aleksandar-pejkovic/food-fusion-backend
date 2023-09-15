package dev.alpey.foodfusionbackend.service.condiment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.alpey.foodfusionbackend.model.dto.CondimentDTO;
import dev.alpey.foodfusionbackend.model.entity.Condiment;
import dev.alpey.foodfusionbackend.repository.CondimentRepository;
import dev.alpey.foodfusionbackend.utils.ImageResize;

@Service
public class CondimentService {

    @Autowired
    private CondimentRepository repository;

    @Autowired
    private CondimentMapper mapper;

    public CondimentDTO saveCondiment(CondimentDTO condimentDTO) {
        Condiment condiment = mapper.convertToEntity(condimentDTO);
        Condiment savedCondiment = repository.save(condiment);
        return mapper.convertToDto(savedCondiment);
    }

    public CondimentDTO updateCondiment(CondimentDTO condimentDTO) {
        Condiment existingCondiment = mapper.convertToExistingEntity(condimentDTO);
        Condiment updatedCondiment = repository.save(existingCondiment);
        return mapper.convertToDto(updatedCondiment);
    }

    public CondimentDTO updateCondimentImage(MultipartFile imageFile, Long condimentId) {
        Condiment existingCondiment = repository.findById(condimentId).orElseThrow();
        byte[] resizedImage = ImageResize.resizeImage(imageFile);
        existingCondiment.setImage(resizedImage);
        return mapper.convertToDto(repository.save(existingCondiment));
    }

    public void deleteCondiment(Long id) {
        repository.deleteById(id);
    }

    public CondimentDTO loadCondimentById(Long id) {
        Condiment condiment = repository.findById(id).orElseThrow();
        return mapper.convertToDto(condiment);
    }

    public List<CondimentDTO> loadCondimentListByFoodId(Long foodId) {
        return mapper.convertToDtoList(repository.findByFoodId(foodId));
    }

    public List<CondimentDTO> loadCondimentListByItemId(Long itemId) {
        return mapper.convertToDtoList(repository.findByItemId(itemId));
    }

    public List<CondimentDTO> loadAllCondiment() {
        return mapper.convertToDtoList(repository.findAll());
    }
}
