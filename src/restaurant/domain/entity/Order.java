package restaurant.domain.entity;

import restaurant.domain.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    int orderId;
    List<OrderItem> items;
    int tableId;
    int customerId;
    OrderStatus status;
    LocalDateTime createAt;
    Order(int orderId,
          int tableId,
          List<OrderItem> items,
          int customerId,
          OrderStatus orderStatus,
          LocalDateTime paymentTime)
    {
        this.orderId = orderId;
        this.tableId = tableId;
        this.items = items;
        this.customerId = customerId;
        this.status = orderStatus;
        this.createAt = paymentTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public LocalDateTime getPaymentTime() {
        return createAt;
    }
    public void setPaymentTime(LocalDateTime paymentTime) {
        this.createAt = paymentTime;
    }
}

