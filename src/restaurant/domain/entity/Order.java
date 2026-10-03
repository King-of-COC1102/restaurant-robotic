package restaurant.domain.entity;

import restaurant.domain.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    String orderId;
    String tableId;
    List<OrderItem> items;
    OrderStatus orderStatus;
    LocalDateTime paymentTime;
    Order(String orderId, String tableId, List<OrderItem> items, OrderStatus orderStatus, LocalDateTime paymentTime)
    {
        this.orderId = orderId;
        this.tableId = tableId;
        this.items = items;
        this.orderStatus = orderStatus;
        this.paymentTime = paymentTime;
    }

<<<<<<< Updated upstream
hello
=======
    public String getOrderId() {
        return orderId;
    }

    public String getTableId() {
        return tableId;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }
}
>>>>>>> Stashed changes
