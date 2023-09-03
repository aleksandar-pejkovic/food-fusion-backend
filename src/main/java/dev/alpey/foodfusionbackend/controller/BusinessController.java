package dev.alpey.foodfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alpey.foodfusionbackend.model.dto.BusinessDTO;
import dev.alpey.foodfusionbackend.service.business.BusinessService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping
    public ResponseEntity<BusinessDTO> saveBusiness(@Valid @RequestBody BusinessDTO businessDTO) {
        return ResponseEntity.ok(businessService.saveBusiness(businessDTO));
    }

    @PutMapping
    public ResponseEntity<BusinessDTO> updateBusiness(@Valid @RequestBody BusinessDTO businessDTO) {
        return ResponseEntity.ok(businessService.updateBusiness(businessDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteBusiness(@PathVariable Long id) {
        businessService.deleteBusiness(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDTO> getBusinessById(@PathVariable Long id) {
        return ResponseEntity.ok(businessService.loadBusinessById(id));
    }

    @GetMapping("/current")
    public ResponseEntity<List<BusinessDTO>> getCurrentUserBusinesses() {
        return ResponseEntity.ok(businessService.loadBusinessListForCurrentUser());
    }

    @Secured("SCOPE_UNRESTRICTED")
    @GetMapping
    public ResponseEntity<List<BusinessDTO>> getAllBusinesses() {
        return ResponseEntity.ok(businessService.loadAllBusinesses());
    }
}
