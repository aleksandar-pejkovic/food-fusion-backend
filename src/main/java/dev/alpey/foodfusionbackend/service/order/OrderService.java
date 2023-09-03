package dev.alpey.foodfusionbackend.service.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.model.dto.OrderDTO;
import dev.alpey.foodfusionbackend.model.entity.Order;
import dev.alpey.foodfusionbackend.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderMapper mapper;

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = mapper.convertToEntity(orderDTO);
        Order savedOrder = repository.save(order);
        return mapper.convertToDto(savedOrder);
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order existingOrder = mapper.convertToExistingEntity(orderDTO);
        Order updatedOrder = repository.save(existingOrder);
        return mapper.convertToDto(updatedOrder);
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }

    public OrderDTO loadOrderById(Long id) {
        Order order = repository.findById(id).orElseThrow();
        return mapper.convertToDto(order);
    }

    public List<OrderDTO> loadOrderListByUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsername(username)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> loadAllOrders() {
        return repository.findAll()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }
}
