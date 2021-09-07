package com.uber.summer.practice.order.management.controller;

import com.uber.summer.practice.order.management.order.ClientOrder;
import com.uber.summer.practice.order.management.order.status.state.State;
import com.uber.summer.practice.order.management.services.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
//@RequestMapping(path = "/orders") //ADDDD!!!!!!
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders") //works
    public List<ClientOrder> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/orders/{id}") //works
    public ClientOrder getOrderByID(@PathVariable UUID id) {
        return orderService.getOrderByID(id);
    }

    @PostMapping("/orders") //works
    public void addOrder(@RequestBody ClientOrder order) {
//        orderService.addOrder(order);
    }

    @PutMapping("/orders/{id}/assign") //works
    public void assign(@PathVariable UUID id) {
//        orderService.assignOrder(id);
    }

    @PutMapping("/orders/{id}/{state}") //works
    public void updateState(@PathVariable UUID id, @PathVariable State state) {
//        orderService.updateOrderState(id, state);
    }


}
