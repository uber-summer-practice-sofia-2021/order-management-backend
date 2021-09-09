package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.entities.Status;
import com.uber.summer.practice.order.management.repository.OrderRepository;
import com.uber.summer.practice.order.management.entities.ClientOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ClientOrder> getOrders() {
        return orderRepository.findAll();
    }

    public ClientOrder getOrderByID(UUID id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found");
        }
        return orderRepository.findById(id).get();
    }


    public void addOrder(ClientOrder order) {
        orderRepository.save(order);
    }

    public void updateOrderState(UUID id, Status status) {
        ClientOrder order = getOrderByID(id);
        order.setStatus(status);
        orderRepository.save(order);
    }
}
