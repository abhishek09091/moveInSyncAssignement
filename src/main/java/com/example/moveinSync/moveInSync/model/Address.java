package com.example.moveinSync.moveInSync.model;

public class Address {

    private Integer addressId;
    private String city;
    private String pinCode;
    private String state;
    private String streetNo;
    private String landmark;

    public Address() {
    }

    public Address(Integer addressId, String city, String pinCode, String state, String streetNo, String landmark) {
        this.addressId = addressId;
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
        this.streetNo = streetNo;
        this.landmark = landmark;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", city='" + city + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", state='" + state + '\'' +
                ", streetNo='" + streetNo + '\'' +
                ", landmark='" + landmark + '\'' +
                '}';
    }
}
