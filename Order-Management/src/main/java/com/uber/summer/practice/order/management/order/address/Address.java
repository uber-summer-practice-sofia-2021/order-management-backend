package com.uber.summer.practice.order.management.order.address;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Double.compare(address.latitude, latitude) == 0 && Double.compare(address.longitude, longitude) == 0 && Objects.equals(addressName, address.addressName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, addressName);
    }
}
