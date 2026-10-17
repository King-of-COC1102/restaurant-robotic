package restaurant.domain.entity;

import restaurant.domain.enums.PaymentMethod;
import restaurant.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private int orderId;
    private double totalAmount;
    private double discountAmount;
    private double finalAmount;
    private PaymentMethod method;
    private LocalDateTime paidAt;
    private PaymentStatus status;

    public Payment(int paymentId, int orderId, double totalAmount, double discountAmount, double finalAmount, PaymentMethod method, LocalDateTime paidAt, PaymentStatus status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.method = method;
        this.paidAt = paidAt;
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }
    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
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
