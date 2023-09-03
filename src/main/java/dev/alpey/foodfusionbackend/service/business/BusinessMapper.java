package dev.alpey.foodfusionbackend.service.business;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.model.dto.BusinessDTO;
import dev.alpey.foodfusionbackend.model.entity.Business;
import dev.alpey.foodfusionbackend.model.entity.User;
import dev.alpey.foodfusionbackend.repository.BusinessRepository;
import dev.alpey.foodfusionbackend.repository.UserRepository;

@Component
public class BusinessMapper {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    Business convertToEntity(BusinessDTO businessDTO) {
        User user = userRepository.findById(businessDTO.getUserId()).orElseThrow();
        Business business = mapper.map(businessDTO, Business.class);
        business.setUser(user);
        return business;
    }

    Business convertToExistingEntity(BusinessDTO businessDTO) {
        Business existingBusiness = businessRepository.findById(businessDTO.getUserId()).orElseThrow();
        mapper.map(businessDTO, existingBusiness);
        return existingBusiness;
    }

    BusinessDTO convertToDto(Business business) {
        BusinessDTO businessDTO = mapper.map(business, BusinessDTO.class);
        businessDTO.setUserId(business.getUser().getId());
        return businessDTO;
    }

    List<BusinessDTO> convertToDtoList(List<Business> businessList) {
        return businessList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
