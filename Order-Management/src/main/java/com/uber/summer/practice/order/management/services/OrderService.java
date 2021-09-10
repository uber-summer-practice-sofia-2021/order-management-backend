package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.entities.Status;
import com.uber.summer.practice.order.management.entities.Tags;
import com.uber.summer.practice.order.management.repository.OrderRepository;
import com.uber.summer.practice.order.management.entities.ClientOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Map<String, Object> getOrders(Optional<Map<String,String>> qp, int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        System.out.println(qp);
        double max_w = Double.MAX_VALUE;
        double max_h = Double.MAX_VALUE;
        double max_d = Double.MAX_VALUE;
        double max_l = Double.MAX_VALUE;
        // TODO Add all tags
        String tag = "";
        //Default value of Tags -> NONE ????
        for(Map.Entry<String,String> param : qp.get().entrySet()) {
            switch (param.getKey()) {
                case "max_weight" : max_w = Double.parseDouble(param.getValue());
                case "max_height" : max_h = Double.parseDouble(param.getValue());
                case "max_depth" : max_d = Double.parseDouble(param.getValue());
                case "max_length" : max_l = Double.parseDouble(param.getValue());
                case "tag" : tag = param.getValue();
                //default?
            }
        }

        Page<ClientOrder> pageOrder = orderRepository.findClientOrdersByWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndDepthIsLessThanAndTags(max_w,max_h,max_l,max_d,Tags.valueOf(tag),paging);

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
