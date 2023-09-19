package dev.alpey.foodfusionbackend.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.model.dto.ItemDTO;
import dev.alpey.foodfusionbackend.model.entity.Item;
import dev.alpey.foodfusionbackend.model.entity.Order;
import dev.alpey.foodfusionbackend.repository.ItemRepository;
import dev.alpey.foodfusionbackend.repository.OrderRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemMapper mapper;

    public List<ItemDTO> saveItems(List<ItemDTO> itemDTOList) {
        Order order = orderRepository.findById(itemDTOList.get(0).getOrderId()).orElseThrow();
        List<Item> itemList = mapper.convertToEntityList(itemDTOList, order);
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
