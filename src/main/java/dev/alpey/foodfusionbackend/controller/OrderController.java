package dev.alpey.foodfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.alpey.foodfusionbackend.model.dto.OrderDTO;
import dev.alpey.foodfusionbackend.service.order.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDTO saveOrder(@RequestParam("businessId") Long businessId) {
        return orderService.saveOrder(businessId);
    }

    @PutMapping
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.loadOrderById(id);
    }

    @GetMapping("/business-id/{businessId}")
    public List<OrderDTO> getCurrentUserOrders(@PathVariable("businessId") Long businessId) {
        return orderService.loadOrderListByBusinessId(businessId);
    }

    @Secured("SCOPE_UNRESTRICTED")
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.loadAllOrders();
    }
}
