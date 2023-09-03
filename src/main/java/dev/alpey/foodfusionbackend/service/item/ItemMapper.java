package dev.alpey.foodfusionbackend.service.item;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.model.dto.ItemDTO;
import dev.alpey.foodfusionbackend.model.entity.Food;
import dev.alpey.foodfusionbackend.model.entity.Item;
import dev.alpey.foodfusionbackend.model.entity.Order;
import dev.alpey.foodfusionbackend.repository.FoodRepository;
import dev.alpey.foodfusionbackend.repository.ItemRepository;
import dev.alpey.foodfusionbackend.repository.OrderRepository;

@Component
public class ItemMapper {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper mapper;

    Item convertToEntity(ItemDTO itemDTO) {
        Food food = foodRepository.findById(itemDTO.getFoodId()).orElseThrow();
        Order order = orderRepository.findById(itemDTO.getOrderId()).orElseThrow();
        Item item = mapper.map(itemDTO, Item.class);
        item.setOrder(order);
        item.setFood(food);
        return item;
    }

    ItemDTO convertToDto(Item item) {
        ItemDTO itemDTO = mapper.map(item, ItemDTO.class);
        itemDTO.setFoodId(item.getFood().getId());
        itemDTO.setOrderId(item.getOrder().getId());
        return itemDTO;
    }
}
