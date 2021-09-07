package com.uber.summer.practice.order.management.service;

import com.uber.summer.practice.order.management.order.ClientOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public List<ClientOrder> serviceGetOrders() {
        return List.of(new ClientOrder());
    }
}
