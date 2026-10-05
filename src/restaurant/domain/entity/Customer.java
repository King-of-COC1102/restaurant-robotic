package restaurant.domain.entity;

import restaurant.domain.enums.*;

import java.time.LocalDateTime;

public class Customer {
    int customerId;
    String name;
    String phoneNumber;
    CustomerType type;
    LocalDateTime ArriveAt;

    public Customer(int customerId, String name, String phoneNumber, CustomerType type, LocalDateTime arriveAt) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.type = type;
        ArriveAt = arriveAt;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CustomerType getType() {
        return type;
    }
    public void setType(CustomerType type) {
        this.type = type;
    }

    public LocalDateTime getArriveAt() {
        return ArriveAt;
    }
    public void setArriveAt(LocalDateTime arriveAt) {
        ArriveAt = arriveAt;
    }
}
