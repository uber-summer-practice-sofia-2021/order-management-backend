package com.uber.summer.practice.order.management;

import com.uber.summer.practice.order.management.dao.OrdersDAO;
import com.uber.summer.practice.order.management.order.ClientOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderManagementApplication {

    public static void main(String[] args) {
        //OrdersDAO dao = new OrdersDAO();
        //dao.update(new ClientOrder());
        SpringApplication.run(OrderManagementApplication.class, args);
    }



}
