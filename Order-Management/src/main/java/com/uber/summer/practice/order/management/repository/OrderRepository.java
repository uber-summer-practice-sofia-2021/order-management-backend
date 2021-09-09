package com.uber.summer.practice.order.management.repository;

import com.uber.summer.practice.order.management.entities.ClientOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public interface OrderRepository extends JpaRepository<ClientOrder,UUID> {
//    Page<ClientOrder> findClientOrderByFromLessThanEqua
}
