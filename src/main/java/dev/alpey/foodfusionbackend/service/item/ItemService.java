package dev.alpey.foodfusionbackend.service.item;

import java.util.List;

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

    public List<ItemDTO> saveItems(List<ItemDTO> itemDTOList) {
        List<Item> itemList = mapper.convertToEntityList(itemDTOList);
        List<Item> savedItemList = repository.saveAll(itemList);
        return mapper.convertToDtoList(savedItemList);
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    public ItemDTO loadItemById(Long id) {
        Item item = repository.findById(id).orElseThrow();
        return mapper.convertToDto(item);
    }

    public List<ItemDTO> loadItemListByOrderId(Long orderId) {
        return mapper.convertToDtoList(repository.findByOrderId(orderId));
    }
}
