package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.entities.Status;
import com.uber.summer.practice.order.management.entities.ClientOrder;
import com.uber.summer.practice.order.management.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Map<String, Object> getOrders(Optional<Map<String, String>> qp, int page, int size) {
        qp.ifPresent(param -> param.forEach((k, v) -> {
            System.out.println(String.format("%s : %s", k, v));
        }));

        List<ClientOrder> orders = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);

        Page<ClientOrder> pageOrder = orderRepository.findAll(paging);

        orders = pageOrder.getContent();

        Map<String, Object> responseMetaData = new HashMap<>();
        responseMetaData.put("totalItems", pageOrder.getTotalElements());
        responseMetaData.put("totalPages", pageOrder.getTotalPages());
        responseMetaData.put("currentPage", pageOrder.getNumber());

        Map<String, Object> response = new HashMap<>();
        response.put("data", orders);
        response.put("pagination", responseMetaData);

        return response;
    }

    public ClientOrder getOrderByID(UUID id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found");
        }
        return orderRepository.findById(id).get();
    }


    public ResponseEntity<Map<String,Object>> addOrder(ClientOrder order) {
        if (order.getFrom().equals(order.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order has two equal addresses!");
        } else if (order.getLength() < 0
                || order.getDepth() < 0
                || order.getHeight() < 0
                || order.getWeight() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dimensions should be positive number!");
        } else {
            orderRepository.save(order);

            Map<String,Object> response = new HashMap<>();
            response.put("Order info",order);
            response.put("Order","created");
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String,Object>> updateOrderState(UUID id, Status status) {
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
            Map<String,Object> response = new HashMap<>();
            response.put("Order info",order);
            response.put("Order","updated");
            return  new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong state transition!");
        }
    }
}
