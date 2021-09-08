package com.uber.summer.practice.order.management.order.dimensions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
public class OrderDimensions implements Serializable{
    private double length;
    private double depth;
    private double height;
    private double weight;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID orderDimensionsID;
    
    public OrderDimensions(double length, double depth, double height, double weight) {
        this.length = length;
        this.depth = depth;
        this.height = height;
        this.weight = weight;
        this.orderDimensionsID = UUID.randomUUID();
    }

    public OrderDimensions(){
        this(0,0,0,0);
    }

    @Override
    public String toString() {
        return "OrderDimensions{" +
                "length=" + length +
                ", depth=" + depth +
                ", height=" + height +
                ", weight=" + weight +
                '}';
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDimensions that = (OrderDimensions) o;
        return Double.compare(that.length, length) == 0 && Double.compare(that.depth, depth) == 0 && Double.compare(that.height, height) == 0 && Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, depth, height, weight);
    }
}
