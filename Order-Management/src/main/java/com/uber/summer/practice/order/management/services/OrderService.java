package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.dao.OrderRepository;
import com.uber.summer.practice.order.management.order.ClientOrder;
import com.uber.summer.practice.order.management.order.status.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    public List<ClientOrder> getOrders() {
//        return orderRepository.findAllByCriteria();
//    }

    public ClientOrder getOrderByID(UUID id) {
        return orderRepository.findById(id).get();
    }

    public void addOrder(ClientOrder order) {
        orderRepository.save(order);
    }
//
//    public void updateOrderState(UUID id, State state) {
//        orderRepository.update(id, state);
//    }
//    public void assignOrder(UUID id) {
//        orderRepository.assign(id);
//    }


}
