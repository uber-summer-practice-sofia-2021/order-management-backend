package com.uber.summer.practice.order.management.entities;

import javax.persistence.*;
import javax.swing.text.html.HTML;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class ClientOrder {
    private String clientName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address from;
    @OneToOne(cascade = CascadeType.ALL)
    private Address to;
    private String clientEmail;
    private String phoneNumber;
    private double length;
    private double depth;
    private double height;
    private double weight;
    @ElementCollection(targetClass = Tags.class)
    private List<Tags> tags;
    private DeliveryType deliveryType;
    @Id
    private final UUID ID;
    private Status status;
    private final LocalDateTime createdAt;

    public ClientOrder(String clientName, Address from, Address to, String clientEmail,
                       String phoneNumber, double length, double depth, double height,
                       double weight, List<Tags> tags, DeliveryType deliveryType) {
        this.clientName = clientName;
        this.from = from;
        this.to = to;
        this.clientEmail = clientEmail;
        this.phoneNumber = phoneNumber;
        this.length = length;
        this.depth = depth;
        this.height = height;
        this.weight = weight;
        this.tags = tags;
        this.deliveryType = deliveryType;
        this.ID = UUID.randomUUID();
        this.status = Status.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    static final public ClientOrder CLIENT_ORDER_TEST_1 = new ClientOrder(
            "Borislav",
            new Address(12.4, 32.5, "Mladost 1"),
            new Address(24.4, 76.4, "Mladost 5"),
            "bobbyrx19@gmail.com",
            "02478242",
            12, 23.3, 4, 6,
            new ArrayList<>(Arrays.asList(Tags.DANGEROUS, Tags.FRAGILE)),
            DeliveryType.EXPRESS);

    static final public ClientOrder CLIENT_ORDER_TEST_2 = new ClientOrder(
            "Ivan Todorov",
            new Address(32.4, 322.5, "Ovcha kupel"),
            new Address(14.4, 26.4, "Studentski grad"),
            "example@gmail.com",
            "089212342",
            22, 2.3, 10, 16,
            new ArrayList<>(Arrays.asList(Tags.DANGEROUS)),
            DeliveryType.STANDARD);

    public ClientOrder() {
        this.ID = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.status = Status.OPEN;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
                this.phoneNumber, this.length, this.depth, this.height, this.weight, this.tags,
                this.deliveryType, this.ID, this.status, this.createdAt);
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
                && Objects.equals(this.length, clientOrder.length)
                && Objects.equals(this.depth, clientOrder.depth)
                && Objects.equals(this.height, clientOrder.height)
                && Objects.equals(this.weight, clientOrder.weight)
                && Objects.equals(this.tags, clientOrder.tags)
                && Objects.equals(this.deliveryType, clientOrder.deliveryType)
                && Objects.equals(this.ID, clientOrder.ID)
                && Objects.equals(this.status, clientOrder.status)
                && Objects.equals(this.createdAt, clientOrder.createdAt);
    }
}

