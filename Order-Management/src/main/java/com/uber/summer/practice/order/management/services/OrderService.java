package com.uber.summer.practice.order.management.services;

import com.uber.summer.practice.order.management.entities.Status;
import com.uber.summer.practice.order.management.entities.Tags;
import com.uber.summer.practice.order.management.repository.OrderRepository;
import com.uber.summer.practice.order.management.entities.ClientOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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
                                         Optional<List<Tags>> tags, int page, int size) {

        double max_w = Double.MAX_VALUE;
        double max_h = Double.MAX_VALUE;
        double max_wid = Double.MAX_VALUE;
        double max_l = Double.MAX_VALUE;
        List<Tags> tagsList = new ArrayList<>();


        if (max_weight.isPresent()) {
            max_w = Double.parseDouble(max_weight.get());
        }

        if (max_height.isPresent()) {
            max_h = Double.parseDouble(max_height.get());
        }

        if (max_length.isPresent()) {
            max_l = Double.parseDouble(max_length.get());
        }

        if (max_width.isPresent()) {
            max_wid = Double.parseDouble(max_width.get());
        }

        tagsList.add(Tags.NOTAG);
        tags.ifPresent(tagsList::addAll);

        List<ClientOrder> orderList = orderRepository.findDistinctByStatusIsAndWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndWidthIsLessThan(Status.OPEN, max_w, max_h, max_l, max_wid);

        List<ClientOrder> result = new ArrayList<>();
        for (ClientOrder o : orderList) {
            boolean toAdd = true;
            List<Tags> orderTags = o.getTags();
            for (Tags t : orderTags) {
                if (!tagsList.contains(t)) {
                    toAdd = false;
                    break;
                }
            }
            if (toAdd) {
                result.add(o);
            }
        }

        Integer nextPage = null,
                prevPage = null;

        PagedListHolder<ClientOrder> customPage = new PagedListHolder();
        customPage.setSource(result);
        customPage.setPageSize(size);

        if (page < customPage.getPageCount()) {
            customPage.setPage(page);
        } else {
            page = 0;
        }

        Map<String, Object> responseMetaData = new LinkedHashMap<>();
        if (customPage.getPageCount() != 1) {
            if (customPage.isLastPage()) {
                prevPage = page - 1;
            }
            if (customPage.isFirstPage()) {
                nextPage = page + 1;
            }
        }

        if (!customPage.isFirstPage() && !customPage.isLastPage()) {
            prevPage = page - 1;
            nextPage = page + 1;
        }

        responseMetaData.put("next", nextPage);
        responseMetaData.put("prev", prevPage);
        responseMetaData.put("totalItems", customPage.getNrOfElements());
        responseMetaData.put("totalPages", customPage.getPageCount());
        responseMetaData.put("currentPage", customPage.getPage());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("data", customPage.getPageList());
        response.put("pagination", responseMetaData);

        return response;
    }

    public ClientOrder getOrderByID(UUID id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found");
        }
        return orderRepository.findById(id).get();
    }


    public ResponseEntity<Map<String, Object>> addOrder(ClientOrder order) {
        if (order.getFrom().equals(order.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order has two equal addresses!");
        } else if (order.getLength() < 0
                || order.getWidth() < 0
                || order.getHeight() < 0
                || order.getWeight() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dimensions should be positive number!");
        } else {
            ClientOrder orderWithID = orderRepository.save(order);
            orderRepository.save(order);
            Map<String, Object> response = new HashMap<>();
            response.put("Order ID", orderWithID.getID());
            response.put("Order status", "created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

    }

    public ResponseEntity<Map<String, Object>> updateOrderState(UUID id, Status status) {
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
                else if(status.equals(Status.CANCELLED)) {
                    status = Status.OPEN;
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
            Map<String, Object> response = new HashMap<>();
            response.put("Order ID", order.getID());
            response.put("Order status", "updated");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong state transition!");
        }
    }

    public ResponseEntity<Map<String, Object>> deleteOrderByID(UUID id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found");
        }
        orderRepository.deleteById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("Order status", "deleted");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
