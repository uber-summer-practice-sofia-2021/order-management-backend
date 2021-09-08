package com.uber.summer.practice.order.management.controller;

import com.uber.summer.practice.order.management.order.ClientOrder;
import com.uber.summer.practice.order.management.order.status.state.State;
import com.uber.summer.practice.order.management.services.OrderService;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.sqlite.SQLiteException;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<ClientOrder> getOrders() {
        try {
            return orderService.getOrders();
        } catch(HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }

    @GetMapping("/orders/{id}")
    public ClientOrder getOrderByID(@PathVariable UUID id) { //add exception handling if "id" is missing
        try {
            return orderService.getOrderByID(id);
        } catch(HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }

    @PostMapping("/orders")
    public void addOrder(@RequestBody ClientOrder order) {
        try {
            orderService.addOrder(order);
        } catch(HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }

    @PutMapping("/orders/{id}/assign")
    public void assign(@PathVariable UUID id) {
        try {
            orderService.assignOrder(id);
        } catch(HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }

    @PutMapping("/orders/{id}/{state}")
    public void updateState(@PathVariable UUID id, @PathVariable State state) {
        try {
            orderService.updateOrderState(id, state);
        } catch(HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }
}
