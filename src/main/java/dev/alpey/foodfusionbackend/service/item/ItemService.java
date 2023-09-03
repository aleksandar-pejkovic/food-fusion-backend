package dev.alpey.foodfusionbackend.service.item;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.model.dto.ItemDTO;
import dev.alpey.foodfusionbackend.model.entity.Item;
import dev.alpey.foodfusionbackend.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ItemMapper mapper;

    public ItemDTO saveItem(ItemDTO itemDTO) {
        Item item = mapper.convertToEntity(itemDTO);
        Item savedItem = repository.save(item);
        return mapper.convertToDto(savedItem);
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    public ItemDTO loadItemById(Long id) {
        Item item = repository.findById(id).orElseThrow();
        return mapper.convertToDto(item);
    }

    public List<ItemDTO> loadItemListByOrderId(Long orderId) {
        return repository.findByOrderId(orderId)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }
}
