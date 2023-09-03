package dev.alpey.foodfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alpey.foodfusionbackend.model.dto.ItemDTO;
import dev.alpey.foodfusionbackend.service.item.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO savedItemDTO = itemService.saveItem(itemDTO);
        return ResponseEntity.ok(savedItemDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
        ItemDTO itemDTO = itemService.loadItemById(id);
        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ItemDTO>> getItemsByOrderId(@PathVariable Long orderId) {
        List<ItemDTO> itemDTOList = itemService.loadItemListByOrderId(orderId);
        return ResponseEntity.ok(itemDTOList);
    }
}
