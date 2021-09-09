package com.uber.summer.practice.order.management.controller;

import com.uber.summer.practice.order.management.entities.ClientOrder;
import com.uber.summer.practice.order.management.services.OrderService;
import org.hibernate.HibernateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//import org.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Map<String,Object> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size
            ) {
        try {
            return orderService.getOrders(page,size);
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
//
//    @PutMapping("/orders/{id}/assign")
//    public void assign(@PathVariable UUID id) {
//        try {
//            orderService.assignOrder(id);
//        } catch(HibernateException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
//        }
//    }
//
//    @PutMapping("/orders/{id}/{state}")
//    public void updateState(@PathVariable UUID id, @PathVariable State state) {
//        try {
//            orderService.updateOrderState(id, state);
//        } catch(HibernateException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fulfil the request", e);
//        }
//    }
}
