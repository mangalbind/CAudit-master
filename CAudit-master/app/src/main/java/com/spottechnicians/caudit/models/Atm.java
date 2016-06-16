package com.spottechnicians.caudit.models;

/**
 * Created by Ganesh on 6/1/2016.
 */
public class Atm {
    String supervisorId;
    String atmId;
    String bankName;
    String customerName;
    String address;
    String city;
    String state;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    public Atm(String supervisorId, String atmId, String bankName, String customerName, String address, String city, String state,String type) {
        this.supervisorId = supervisorId;
        this.atmId = atmId;
        this.bankName = bankName;
        this.customerName = customerName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.type=type;
    }

    public Atm() {
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
