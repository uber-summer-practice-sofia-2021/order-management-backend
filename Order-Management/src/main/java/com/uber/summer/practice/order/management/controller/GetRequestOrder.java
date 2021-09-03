package com.uber.summer.practice.order.management.controller;

import com.uber.summer.practice.order.management.order.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetRequestOrder {

    @GetMapping("/orderTest")
    public Order orderTest() {
        return Order.orderTest;
    }
}
