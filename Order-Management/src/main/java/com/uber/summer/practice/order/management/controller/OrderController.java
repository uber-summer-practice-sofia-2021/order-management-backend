package com.uber.summer.practice.order.management.controller;

import com.uber.summer.practice.order.management.order.ClientOrder;
import com.uber.summer.practice.order.management.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<ClientOrder> getOrders() {
        return orderService.serviceGetOrders();
    }
}
