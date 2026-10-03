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
    Order(String orderId, String tableId, List<OrderItem> items, OrderStatus orderStatus, )
}
