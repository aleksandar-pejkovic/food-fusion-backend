package dev.alpey.foodfusionbackend.service.item;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.alpey.foodfusionbackend.model.dto.ItemDTO;
import dev.alpey.foodfusionbackend.model.entity.Condiment;
import dev.alpey.foodfusionbackend.model.entity.Food;
import dev.alpey.foodfusionbackend.model.entity.Item;
import dev.alpey.foodfusionbackend.model.entity.Order;
import dev.alpey.foodfusionbackend.repository.CondimentRepository;
import dev.alpey.foodfusionbackend.repository.FoodRepository;

@Component
public class ItemMapper {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CondimentRepository condimentRepository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    Item convertToEntity(ItemDTO itemDTO) {
        Food food = foodRepository.findById(itemDTO.getFoodId()).orElseThrow();
        Item item = mapper.map(itemDTO, Item.class);
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

    public List<Item> convertToEntityList(List<ItemDTO> itemDTOList, Order order) {
        return itemDTOList.stream()
                .map(this::convertToEntity)
                .peek(item -> item.setOrder(order))
                .collect(Collectors.toList());
    }
}
