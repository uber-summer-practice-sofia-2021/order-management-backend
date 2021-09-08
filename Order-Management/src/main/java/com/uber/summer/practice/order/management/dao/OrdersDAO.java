package com.uber.summer.practice.order.management.dao;

import com.uber.summer.practice.order.management.order.ClientOrder;
import com.uber.summer.practice.order.management.order.address.Address;
import com.uber.summer.practice.order.management.order.status.Status;
import com.uber.summer.practice.order.management.order.status.state.State;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Component
public class OrdersDAO {
    //get order
    public ClientOrder findByID(UUID ID) {
        return new ClientOrder();
    }

//    //get all orders w state == open && assigned == false
//    public List<ClientOrder> findAllAvailable() {
//        return null;
//    }


    //get all orders by criteria
    public List<ClientOrder> findAllByCriteria() { //add parameters
        return List.of();
    }

    //post
    public void insert(ClientOrder object) {
        //insert into table
    }

    //put (change status)
    public void update(UUID id, State state) {
        //update state col
    }

    public void assign(UUID id) {
        //update assigned col = true
    }

    //put object
    public void update(ClientOrder order) {
        //update the whole row
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stud");
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        //List<ClientOrder> result = em.createQuery( "FROM client_order", ClientOrder.class ).getResultList();


        em.getTransaction().begin();

        ClientOrder obj = new ClientOrder();

        obj.setClientName("Harry Potter");

        em.persist(obj);

        em.getTransaction().commit();

        em.clear();
    }
}
