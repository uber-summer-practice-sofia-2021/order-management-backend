package com.uber.summer.practice.order.management.service;

import org.springframework.stereotype.Component;

@Component
public class OrderService {
    public String getOrders() {
        return "order list";
    }
}
