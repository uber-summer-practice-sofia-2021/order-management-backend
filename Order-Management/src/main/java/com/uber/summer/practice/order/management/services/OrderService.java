package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.dao.OrdersDAO;
import com.uber.summer.practice.order.management.order.ClientOrder;
import com.uber.summer.practice.order.management.order.status.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrdersDAO ordersDAO;

    @Autowired
    public OrderService(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public List<ClientOrder> getOrders() {
        return ordersDAO.findAllByCriteria();
    }

    public ClientOrder getOrderByID(UUID id) {
        return ordersDAO.findByID(id);
    }

    public void addOrder(ClientOrder order) {
        ordersDAO.insert(order);
    }

    public void updateOrderState(UUID id, State state) {
        ordersDAO.update(id, state);
    }
    public void assignOrder(UUID id) {
        ordersDAO.assign(id);
    }


}
