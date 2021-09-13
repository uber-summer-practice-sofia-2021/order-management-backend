package com.uber.summer.practice.order.management.controller;

import com.uber.summer.practice.order.management.entities.ClientOrder;
import com.uber.summer.practice.order.management.entities.Status;
import com.uber.summer.practice.order.management.entities.Tags;
import com.uber.summer.practice.order.management.services.OrderService;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//import org.sqlite.SQLiteException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Map<String,Object> getOrders(
            @RequestParam(required = false) String max_weight,
            @RequestParam(required = false) String max_height,
            @RequestParam(required = false) String max_length,
            @RequestParam(required = false) String max_width,
            @RequestParam(required = false) List<Tags> tags,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        try {
            return orderService.getOrders(Optional.ofNullable(max_weight),
                                          Optional.ofNullable(max_height),
                                          Optional.ofNullable(max_length),
                                          Optional.ofNullable(max_width),
                                          Optional.ofNullable(tags),page,size);
        } catch(HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }


    @GetMapping("/orders/{id}")
    public ClientOrder getOrderByID(@PathVariable UUID id) { //add exception handling if "id" is missing
        try {
            return orderService.getOrderByID(id);
        } catch (HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Map<String, Object>> addOrder(@RequestBody ClientOrder order) {
        try {
            return orderService.addOrder(order);
        } catch (HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }

    @PostMapping("/orders/{id}/{status}")
    public ResponseEntity<Map<String, Object>> updateState(@PathVariable UUID id, @PathVariable Status status) {
        try {
            return orderService.updateOrderState(id, status);
        } catch (HibernateException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
        }
    }
}
