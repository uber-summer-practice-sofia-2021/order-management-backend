package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.entities.Status;
import com.uber.summer.practice.order.management.repository.OrderRepository;
import com.uber.summer.practice.order.management.entities.ClientOrder;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        boolean isCorrectStatusChange = false;
        switch (order.getStatus()) {
            case OPEN:
                if (status.equals(Status.ASSIGNED) || status.equals(Status.CANCELLED)) {
                    isCorrectStatusChange = true;
                }
                break;
            case ASSIGNED:
                if (status.equals(Status.PICK_UP)) {
                    isCorrectStatusChange = true;
                }
                break;
            case PICK_UP:
                if (status.equals(Status.COMPLETED) || status.equals(Status.FAILED)) {
                    isCorrectStatusChange = true;
                }
                break;
            default:
                break;
        }
        if (isCorrectStatusChange) {
            order.setStatus(status);
            orderRepository.save(order);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong state transition!");
        }
    }
}
