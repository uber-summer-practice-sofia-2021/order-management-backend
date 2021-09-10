package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.entities.Status;
import com.uber.summer.practice.order.management.entities.Tags;
import com.uber.summer.practice.order.management.repository.OrderRepository;
import com.uber.summer.practice.order.management.entities.ClientOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Map<String, Object> getOrders(Optional<String> max_weight,
                                         Optional<String> max_height,
                                         Optional<String> max_length,
                                         Optional<String> max_width,
                                         Optional<List<Tags>> tags,int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        double max_w = Double.MAX_VALUE;
        double max_h = Double.MAX_VALUE;
        double max_wid = Double.MAX_VALUE;
        double max_l = Double.MAX_VALUE;
        List<Tags> tagsList = new ArrayList<>();

        Page<ClientOrder> pageOrder;

        if(max_weight.isPresent()) {
            max_w = Double.parseDouble(max_weight.get());
        }

        if(max_height.isPresent()) {
            max_h = Double.parseDouble(max_height.get());
        }

        if(max_length.isPresent()) {
            max_l = Double.parseDouble(max_length.get());
        }

        if(max_width.isPresent()) {
            max_wid = Double.parseDouble(max_width.get());
        }

        tags.ifPresent(tagsList::addAll);
        System.out.println(tagsList);

        pageOrder = orderRepository.findClientOrdersByWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndDepthIsLessThanAndTagsIn(max_w,max_h,max_l,max_wid,tagsList,paging);
//        Page<ClientOrder> pageOrder = orderRepository.findByTagsIn(tagsList,paging);
        List<ClientOrder> orders = new ArrayList<>();

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
