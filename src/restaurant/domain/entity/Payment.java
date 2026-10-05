package restaurant.domain.entity;

import restaurant.domain.enums.PaymentMethod;
import restaurant.domain.enums.PaymentStatus;

import java.time.LocalDateTime;

public class Payment {
    String id;
    String orderId;
    double amount;
    PaymentMethod method;
    LocalDateTime paidAt;
    PaymentStatus status;

    public Payment(String id, String orderId, double amount, PaymentMethod method, LocalDateTime paidAt, PaymentStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.paidAt = paidAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }
    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }
    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
