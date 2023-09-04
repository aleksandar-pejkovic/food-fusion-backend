package dev.alpey.foodfusionbackend.service.condiment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.model.dto.CondimentDTO;
import dev.alpey.foodfusionbackend.model.entity.Condiment;
import dev.alpey.foodfusionbackend.repository.CondimentRepository;

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

    public void deleteCondiment(Long id) {
        repository.deleteById(id);
    }

    public CondimentDTO loadCondimentById(Long id) {
        Condiment condiment = repository.findById(id).orElseThrow();
        return mapper.convertToDto(condiment);
    }

    public List<CondimentDTO> loadCondimentListByCategoryId(Long categoryId) {
        return mapper.convertToDtoList(repository.findByCategoryId(categoryId));
    }

    public List<CondimentDTO> loadAllCondiment() {
        return mapper.convertToDtoList(repository.findAll());
    }
}
