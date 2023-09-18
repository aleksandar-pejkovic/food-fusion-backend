package dev.alpey.foodfusionbackend.service.order;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.alpey.foodfusionbackend.enums.OrderStatus;
import dev.alpey.foodfusionbackend.model.dto.OrderDTO;
import dev.alpey.foodfusionbackend.model.entity.Business;
import dev.alpey.foodfusionbackend.model.entity.Order;
import dev.alpey.foodfusionbackend.repository.BusinessRepository;
import dev.alpey.foodfusionbackend.repository.OrderRepository;

@Component
public class OrderMapper {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ModelMapper mapper;

    Order convertToEntity(OrderDTO orderDTO) {
        Business business = businessRepository.findById(orderDTO.getBusinessId()).orElseThrow();
        OrderStatus orderStatus = OrderStatus.fromString(orderDTO.getStatus());
        Order order = mapper.map(orderDTO, Order.class);
        order.setOrderStatus(orderStatus);
        order.setBusiness(business);
        return order;
    }

    Order convertToExistingEntity(OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderDTO.getId()).orElseThrow();
        OrderStatus orderStatus = OrderStatus.fromString(orderDTO.getStatus());
        mapper.map(orderDTO, existingOrder);
        existingOrder.setOrderStatus(orderStatus);
        return existingOrder;
    }

    OrderDTO convertToDto(Order order) {
        OrderDTO orderDTO = mapper.map(order, OrderDTO.class);
        orderDTO.setStatus(order.getOrderStatus().getStatus());
        orderDTO.setBusinessId(order.getBusiness().getId());
        return orderDTO;
    }

    List<OrderDTO> convertToDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
