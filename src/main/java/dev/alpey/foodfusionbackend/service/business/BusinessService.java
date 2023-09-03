package dev.alpey.foodfusionbackend.service.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.model.dto.BusinessDTO;
import dev.alpey.foodfusionbackend.model.entity.Business;
import dev.alpey.foodfusionbackend.repository.BusinessRepository;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository repository;

    @Autowired
    private BusinessMapper mapper;

    public BusinessDTO saveBusiness(BusinessDTO businessDTO) {
        Business business = mapper.convertToEntity(businessDTO);
        Business savedBusiness = repository.save(business);
        return mapper.convertToDto(savedBusiness);
    }

    public BusinessDTO updateBusiness(BusinessDTO businessDTO) {
        Business existingBusiness = mapper.convertToExistingEntity(businessDTO);
        Business updatedBusiness = repository.save(existingBusiness);
        return mapper.convertToDto(updatedBusiness);
    }

    public void deleteBusiness(Long id) {
        repository.deleteById(id);
    }

    public BusinessDTO loadBusinessById(Long id) {
        Business business = repository.findById(id).orElseThrow();
        return mapper.convertToDto(business);
    }

    public List<BusinessDTO> loadBusinessListForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsername(username)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<BusinessDTO> loadAllBusinesses() {
        return repository.findAll()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }
}
