package dev.alpey.foodfusionbackend.service.order;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alpey.foodfusionbackend.enums.OrderStatus;
import dev.alpey.foodfusionbackend.model.dto.OrderDTO;
import dev.alpey.foodfusionbackend.model.entity.Business;
import dev.alpey.foodfusionbackend.model.entity.Order;
import dev.alpey.foodfusionbackend.repository.BusinessRepository;
import dev.alpey.foodfusionbackend.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private OrderMapper mapper;

    public OrderDTO saveOrder(Long businessId) {
        int orderNumber = repository.countOrdersByBusinessId(businessId);
        Business business = businessRepository.findById(businessId).orElseThrow();
        Order order = Order.builder()
                .orderNumber(++orderNumber)
                .creationDate(LocalDate.now())
                .orderStatus(OrderStatus.PENDING)
                .business(business)
                .build();
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

    public List<OrderDTO> loadOrderListByBusinessId(Long businessId) {
        return mapper.convertToDtoList(repository.findByBusinessId(businessId));
    }

    public List<OrderDTO> loadAllOrders() {
        return mapper.convertToDtoList(repository.findAll());
    }
}
