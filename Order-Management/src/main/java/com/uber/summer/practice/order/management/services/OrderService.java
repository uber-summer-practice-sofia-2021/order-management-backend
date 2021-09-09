package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.repository.OrderRepository;
import com.uber.summer.practice.order.management.entities.ClientOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Map<String, Object> getOrders(Optional<Map<String,String>> qp,int page, int size) {
        qp.ifPresent(param -> param.forEach((k, v) -> {
            System.out.println(String.format("%s : %s", k, v));
        }));

        List<ClientOrder> orders = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);

        Page<ClientOrder> pageOrder = orderRepository.findAll(paging);

        orders = pageOrder.getContent();

        Map<String,Object> responseMetaData = new HashMap<>();
        responseMetaData.put("totalItems",pageOrder.getTotalElements());
        responseMetaData.put("totalPages",pageOrder.getTotalPages());
        responseMetaData.put("currentPage",pageOrder.getNumber());

        Map<String,Object> response = new HashMap<>();
        response.put("data",orders);
        response.put("pagination",responseMetaData);

        return response;
    }

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
