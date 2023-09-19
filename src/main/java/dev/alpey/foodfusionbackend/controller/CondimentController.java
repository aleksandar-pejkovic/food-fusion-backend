package dev.alpey.foodfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.alpey.foodfusionbackend.model.dto.CondimentDTO;
import dev.alpey.foodfusionbackend.service.condiment.CondimentService;

@RestController
@RequestMapping("/api/condiments")
public class CondimentController {

    @Autowired
    private CondimentService condimentService;

    @PostMapping
    public CondimentDTO saveCondiment(@RequestBody CondimentDTO condimentDTO) {
        return condimentService.saveCondiment(condimentDTO);
    }

    @PutMapping
    public CondimentDTO updateCondiment(@RequestBody CondimentDTO condimentDTO) {
        return condimentService.updateCondiment(condimentDTO);
    }

    @PutMapping("/{id}/update-image")
    public CondimentDTO updateCondimentImage(@RequestParam MultipartFile imageFile,
                                             @PathVariable("id") Long condimentId) {
        return condimentService.updateCondimentImage(imageFile, condimentId);
    }

    @DeleteMapping("/{id}")
    public void deleteCondiment(@PathVariable Long id) {
        condimentService.deleteCondiment(id);
    }

    @GetMapping("/{id}")
    public CondimentDTO loadCondimentById(@PathVariable Long id) {
        return condimentService.loadCondimentById(id);
    }

    @GetMapping("/food-id/{foodId}")
    public List<CondimentDTO> loadCondimentListByFoodId(@PathVariable Long foodId) {
        return condimentService.loadCondimentListByFoodId(foodId);
    }

    @GetMapping("/byItem/{itemId}")
    public List<CondimentDTO> loadCondimentListByItemId(@PathVariable Long itemId) {
        return condimentService.loadCondimentListByItemId(itemId);
    }

    @Secured("SCOPE_UNRESTRICTED")
    @GetMapping
    public List<CondimentDTO> loadAllCondiment() {
        return condimentService.loadAllCondiment();
    }
}
