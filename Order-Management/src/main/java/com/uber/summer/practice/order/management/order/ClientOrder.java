package com.uber.summer.practice.order.management.order;

import com.uber.summer.practice.order.management.order.address.Address;
import com.uber.summer.practice.order.management.order.deliveryType.DeliveryType;
import com.uber.summer.practice.order.management.order.dimensions.OrderDimensions;
import com.uber.summer.practice.order.management.order.status.Status;
import com.uber.summer.practice.order.management.order.status.state.State;
import com.uber.summer.practice.order.management.order.tags.Tags;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ClientOrder {
    private String clientName;
    private transient Address from;
    private transient Address to;
    private String clientEmail;
    private String phoneNumber;
    private transient OrderDimensions orderDimensions;
    private transient List<Tags> tags;
    private DeliveryType deliveryType;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID ID;
    private transient Status status;
    private final LocalDateTime createdAt;

    public ClientOrder(String clientName, Address from, Address to, String clientEmail,
                       String phoneNumber, OrderDimensions orderDimensions, List<Tags> tags,
                       DeliveryType deliveryType) {
        this.clientName = clientName;
        this.from = from;
        this.to = to;
        this.clientEmail = clientEmail;
        this.phoneNumber = phoneNumber;
        this.orderDimensions = orderDimensions;
        this.tags = tags;
        this.deliveryType = deliveryType;
        this.ID = UUID.randomUUID();
        this.status = new Status(State.OPEN, false);
        this.createdAt=LocalDateTime.now();
    }

    static final public ClientOrder CLIENT_ORDER_TEST_1 = new ClientOrder(
            "Borislav",
            new Address(12.4, 32.5, "Mladost 1"),
            new Address(24.4, 76.4, "Mladost 5"),
            "bobbyrx19@gmail.com",
            "02478242",
            new OrderDimensions(12, 23.3, 4, 6),
            new ArrayList<>(Arrays.asList(Tags.DANGEROUS, Tags.FRAGILE)),
            DeliveryType.EXPRESS);

    static final public ClientOrder CLIENT_ORDER_TEST_2 = new ClientOrder(
            "Ivan Todorov",
            new Address(32.4, 322.5, "Ovcha kupel"),
            new Address(14.4, 26.4, "Studentski grad"),
            "example@gmail.com",
            "089212342",
            new OrderDimensions(22, 2.3, 10, 16),
            new ArrayList<>(Arrays.asList(Tags.DANGEROUS)),
            DeliveryType.STANDARD);

    public ClientOrder() {
        this.ID = UUID.randomUUID();
        this.createdAt=LocalDateTime.now();
        this.status=new Status(State.OPEN,false);
    }


    @Override
    public String toString() {
        return "Order{" +
                "clientName='" + clientName + '\'' +
                ", from=" + from.toString() +
                ", to=" + to.toString() +
                ", clientEmail='" + clientEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderDimensions=" + orderDimensions.toString() +
                ", tags=" + tags +
                ", deliveryType=" + deliveryType +
                ", ID=" + ID +
                ", status=" + status.toString() +
                ", createdAt=" + createdAt +
                "}";
    }

    public UUID getID() {
        return ID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public Address getTo() {
        return to;
    }

    public void setTo(Address to) {
        this.to = to;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public OrderDimensions getOrderDimensions() {
        return orderDimensions;
    }

    public void setOrderDimensions(OrderDimensions orderDimensions) {
        this.orderDimensions = orderDimensions;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.clientName, this.from, this.to, this.clientEmail,
                this.phoneNumber, this.orderDimensions, this.tags, this.deliveryType, this.ID,
                this.status, this.createdAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ClientOrder))
            return false;
        ClientOrder clientOrder = (ClientOrder) obj;
        return Objects.equals(this.clientName, clientOrder.clientName)
                && Objects.equals(this.from, clientOrder.from)
                && Objects.equals(this.to, clientOrder.to)
                && Objects.equals(this.clientEmail, clientOrder.clientName)
                && Objects.equals(this.phoneNumber, clientOrder.phoneNumber)
                && Objects.equals(this.orderDimensions, clientOrder.orderDimensions)
                && Objects.equals(this.tags, clientOrder.tags)
                && Objects.equals(this.deliveryType, clientOrder.deliveryType)
                && Objects.equals(this.ID, clientOrder.ID)
                && Objects.equals(this.status, clientOrder.status)
                && Objects.equals(this.createdAt, clientOrder.createdAt);
    }
}

