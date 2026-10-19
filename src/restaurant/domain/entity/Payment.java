package restaurant.domain.entity;

import restaurant.domain.enums.PaymentMethod;
import restaurant.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private int orderId;
    private int discountId;   // 0 hoặc -1 nếu không dùng mã

    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private PaymentMethod method;
    private LocalDateTime paidAt;
    private PaymentStatus status;

    public Payment(int paymentId, int orderId,int discountId, BigDecimal totalAmount, BigDecimal discountAmount, BigDecimal finalAmount, PaymentMethod method, LocalDateTime paidAt, PaymentStatus status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.discountId = discountId;
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

    public int getDiscountId() { return discountId; }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }
    public void setFinalAmount(BigDecimal finalAmount) {
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
