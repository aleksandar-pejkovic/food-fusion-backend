package dev.alpey.foodfusionbackend.service.item;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.model.dto.ItemDTO;
import dev.alpey.foodfusionbackend.model.entity.Condiment;
import dev.alpey.foodfusionbackend.model.entity.Food;
import dev.alpey.foodfusionbackend.model.entity.Item;
import dev.alpey.foodfusionbackend.model.entity.Order;
import dev.alpey.foodfusionbackend.repository.CondimentRepository;
import dev.alpey.foodfusionbackend.repository.FoodRepository;
import dev.alpey.foodfusionbackend.repository.OrderRepository;

@Component
public class ItemMapper {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CondimentRepository condimentRepository;

    @Autowired
    private ModelMapper mapper;

    Item convertToEntity(ItemDTO itemDTO) {
        Food food = foodRepository.findById(itemDTO.getFoodId()).orElseThrow();
        Order order = orderRepository.findById(itemDTO.getOrderId()).orElseThrow();
        Item item = mapper.map(itemDTO, Item.class);
        item.setOrder(order);
        item.setFood(food);
        if (!itemDTO.getCondimentIdList().isEmpty()) {
            List<Long> condimentIdList = itemDTO.getCondimentIdList();
            List<Condiment> condimentList = condimentRepository.findAllById(condimentIdList);
            item.setCondimentList(condimentList);
        }
        return item;
    }

    ItemDTO convertToDto(Item item) {
        ItemDTO itemDTO = mapper.map(item, ItemDTO.class);
        itemDTO.setFoodId(item.getFood().getId());
        itemDTO.setOrderId(item.getOrder().getId());
        List<Long> condimentIdList = item.getCondimentList().stream()
                .map(Condiment::getId)
                .collect(Collectors.toList());
        itemDTO.setCondimentIdList(condimentIdList);
        return itemDTO;
    }

    List<ItemDTO> convertToDtoList(List<Item> itemList) {
        return itemList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<Item> convertToEntityList(List<ItemDTO> itemDTOList) {
        return itemDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
