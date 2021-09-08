package com.uber.summer.practice.order.management.dao;

import com.uber.summer.practice.order.management.order.ClientOrder;
import com.uber.summer.practice.order.management.order.address.Address;
import com.uber.summer.practice.order.management.order.status.Status;
import com.uber.summer.practice.order.management.order.status.state.State;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.sqlite.SQLiteException;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Predicate;

@Component
public class OrdersDAO {
    //get order
    public ClientOrder findByID(UUID ID) {
//        try{
//
//        } catch (SQLiteException e) {
//            e.getResultCode();
//            e.getErrorCode();
//        }
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("per");
        EntityManager em = emf.createEntityManager();

        //List<ClientOrder> result = em.createQuery( "FROM client_order", ClientOrder.class ).getResultList();


        em.getTransaction().begin();

        ClientOrder obj = new ClientOrder();

        obj.setClientName("Harry Potter");

        em.persist(obj);

        em.getTransaction().commit();

        em.clear();

        em.getTransaction().begin();
        ClientOrder obj1 = new ClientOrder();
        em.find(ClientOrder.class,obj1);
        System.out.println(obj1);
    }

}
