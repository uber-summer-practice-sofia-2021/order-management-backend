package com.uber.summer.practice.order.management.repository;

import com.uber.summer.practice.order.management.entities.ClientOrder;
import com.uber.summer.practice.order.management.entities.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface OrderRepository extends CrudRepository<ClientOrder, UUID> {
    List<ClientOrder> findDistinctByStatusIsAndWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndWidthIsLessThan(Status status, double weight, double height, double length, double depth);
}
