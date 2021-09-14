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

//        pageOrder = orderRepository.findClientOrdersByStatusIsAndWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndDepthIsLessThanAndTagsIn(Status.OPEN,max_w,max_h,max_l,max_wid,tagsList,paging);
        pageOrder = orderRepository.findClientOrder(Status.OPEN,max_w,max_h,max_l,max_wid,tagsList,paging);
        List<ClientOrder> orders = new ArrayList<>();


        orders = pageOrder.getContent();

        Map<String,Object> responseMetaData = new LinkedHashMap<>();
        Integer next = null,prev = null;

        if(pageOrder.hasNext()) {
            next = pageOrder.nextPageable().getPageNumber();
        }

        if(pageOrder.hasPrevious()) {
            prev = pageOrder.previousPageable().getPageNumber();
        }

        responseMetaData.put("next",next);
        responseMetaData.put("prev",prev);
        responseMetaData.put("totalItems",pageOrder.getTotalElements());
        responseMetaData.put("totalPages",pageOrder.getTotalPages());
        responseMetaData.put("currentPage",pageOrder.getNumber());

        Map<String,Object> response = new LinkedHashMap<>();
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
