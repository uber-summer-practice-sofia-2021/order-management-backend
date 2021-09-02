package com.uber.summer.practice.order.management.order;

import com.uber.summer.practice.order.management.order.address.Address;
import com.uber.summer.practice.order.management.order.deliveryType.DeliveryType;
import com.uber.summer.practice.order.management.order.dimensions.OrderDimensions;
import com.uber.summer.practice.order.management.order.status.Status;
import com.uber.summer.practice.order.management.order.status.state.State;
import com.uber.summer.practice.order.management.order.tags.Tags;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Order {
    private String clientName;
    private Address from;
    private Address to;
    private String clientEmail;
    private String phoneNumber;
    private OrderDimensions orderDimensions;
    private List<Tags> tags;
    private DeliveryType deliveryType;
    private final UUID ID;
    private Status status;
    private final LocalDateTime createdAt;

    static final public Order orderTest = new Order(
            "Borislav",
            new Address(12.4, 32.5, "Mladost 1"),
            new Address(24.4, 76.4, "Mladost 5"),
            "bobbyrx19@gmail.com",
            "02478242",
            new OrderDimensions(12, 23.3, 4, 6),
            new ArrayList<>(Arrays.asList(Tags.DANGEROUS, Tags.FRAGILE)),
            DeliveryType.EXPRESS
    );

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

    public Order(String clientName, Address from, Address to, String clientEmail,
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
        this.createdAt = LocalDateTime.now();
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
}
