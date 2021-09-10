package com.uber.summer.practice.order.management.repository;

import com.uber.summer.practice.order.management.entities.ClientOrder;
import com.uber.summer.practice.order.management.entities.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface OrderRepository extends JpaRepository<ClientOrder, UUID> {
//    Page<ClientOrder> findByTagsIn(List<Tags> tags,Pageable pageable);
//    Page<ClientOrder> findByTagsIsIn(List<Tags> tags,Pageable pageable);
    Page<ClientOrder> findClientOrdersByWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndDepthIsLessThanAndTagsIn(double weight, double height, double length, double depth,List<Tags> tags,Pageable pageable);
//    Page<ClientOrder> findClientOrdersByWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndDepthIsLessThan(double weight, double height, double length, double depth,Pageable pageable);
}
