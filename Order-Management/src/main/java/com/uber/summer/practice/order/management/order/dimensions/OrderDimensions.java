package com.uber.summer.practice.order.management.order.dimensions;

public class OrderDimensions {
    private double length;
    private double depth;
    private double height;
    private double weight;

    public OrderDimensions(double length, double depth, double height, double weight) {
        this.length = length;
        this.depth = depth;
        this.height = height;
        this.weight = weight;
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
}
