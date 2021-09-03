package com.uber.summer.practice.order.management.order.address;

public class Address {
    private double latitude;
    private double longitude;
    private String addressName;

    public Address(double latitude, double longitude, String addressName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressName = addressName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", addressName='" + addressName + '\'' +
                '}';
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
