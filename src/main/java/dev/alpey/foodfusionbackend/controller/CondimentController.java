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

import dev.alpey.foodfusionbackend.model.dto.CondimentDTO;
import dev.alpey.foodfusionbackend.service.condiment.CondimentService;

@RestController
@RequestMapping("/api/condiments")
public class CondimentController {

    @Autowired
    private CondimentService condimentService;

    @PostMapping
    public ResponseEntity<CondimentDTO> saveCondiment(@RequestBody CondimentDTO condimentDTO) {
        CondimentDTO savedCondiment = condimentService.saveCondiment(condimentDTO);
        return ResponseEntity.ok(savedCondiment);
    }

    @PutMapping
    public ResponseEntity<CondimentDTO> updateCondiment(@RequestBody CondimentDTO condimentDTO) {
        CondimentDTO updatedCondiment = condimentService.updateCondiment(condimentDTO);
        return ResponseEntity.ok(updatedCondiment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondiment(@PathVariable Long id) {
        condimentService.deleteCondiment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondimentDTO> loadCondimentById(@PathVariable Long id) {
        CondimentDTO condiment = condimentService.loadCondimentById(id);
        return ResponseEntity.ok(condiment);
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<CondimentDTO>> loadCondimentListByCategoryId(@PathVariable Long categoryId) {
        List<CondimentDTO> condiments = condimentService.loadCondimentListByCategoryId(categoryId);
        return ResponseEntity.ok(condiments);
    }

    @GetMapping("/byItem/{itemId}")
    public ResponseEntity<List<CondimentDTO>> loadCondimentListByItemId(@PathVariable Long itemId) {
        List<CondimentDTO> condiments = condimentService.loadCondimentListByItemId(itemId);
        return ResponseEntity.ok(condiments);
    }

    @Secured("SCOPE_UNRESTRICTED")
    @GetMapping
    public ResponseEntity<List<CondimentDTO>> loadAllCondiment() {
        List<CondimentDTO> condiments = condimentService.loadAllCondiment();
        return ResponseEntity.ok(condiments);
    }
}
