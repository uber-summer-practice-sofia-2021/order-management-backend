package com.uber.summer.practice.order.management.repository;

import com.uber.summer.practice.order.management.entities.ClientOrder;
import com.uber.summer.practice.order.management.entities.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface OrderRepository extends JpaRepository<ClientOrder, UUID> {
    Page<ClientOrder> findByWeightIsLessThan(double weight);
    Page<ClientOrder> findByHeightIsLessThan(double height, Pageable pageable);
    Page<ClientOrder> findByLengthIsLessThan(double length, Pageable pageable);
    Page<ClientOrder> findByDepthIsLessThan(double depth, Pageable pageable);
    Page<ClientOrder> findByTags(Tags tag, Pageable pageable);

    Page<ClientOrder> findClientOrdersByWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndDepthIsLessThanAndTags(double weight, double height, double length, double depth,Tags tag,Pageable pageable);
//    boolean findByWeightIsLessThan(double w,Pageable pageable);
}
