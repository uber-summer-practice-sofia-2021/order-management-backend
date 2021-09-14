package com.uber.summer.practice.order.management.repository;

import com.uber.summer.practice.order.management.entities.ClientOrder;
import com.uber.summer.practice.order.management.entities.Status;
import com.uber.summer.practice.order.management.entities.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface OrderRepository extends JpaRepository<ClientOrder, UUID> {
    @Query("select o from ClientOrder o where o.status = :status " +
            "and o.weight <= :weight " +
            "and o.height <= :height " +
            "and o.depth <= :depth " +
            "and o.length <= :length ")
    Page<ClientOrder> findClientOrder(@Param("status")Status status, @Param("weight") double weight,
                                      @Param("height") double height, @Param("depth") double depth,
                                      @Param("length") double length, @Param("tags") List<Tags> tags, Pageable pageable);
//    Page<ClientOrder> findClientOrdersByStatusIsAndWeightIsLessThanAndHeightIsLessThanAndLengthIsLessThanAndDepthIsLessThanAndTagsIn(Status status, double weight, double height, double length, double depth, List<Tags> tags, Pageable pageable);
}
