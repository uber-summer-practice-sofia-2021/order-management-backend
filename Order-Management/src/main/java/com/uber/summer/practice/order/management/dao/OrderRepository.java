package com.uber.summer.practice.order.management.dao;

import com.uber.summer.practice.order.management.order.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public interface OrderRepository extends JpaRepository<ClientOrder,UUID> {
}
