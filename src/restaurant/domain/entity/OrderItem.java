package restaurant.domain.entity;

public class OrderItem
{
    int orderItemId;
    int orderId;
    int dishId;
    Double quantity;
    double unitPriceAtOrder;
    String note;
    boolean isCancelled;   // thay cho Status

    public OrderItem(int orderItemId,
                     int orderId,
                     int dishId,
                     Double quantity,
                     double unitPriceAtOrder,
                     String note,
                     boolean isCancelled) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
        this.unitPriceAtOrder = unitPriceAtOrder;
        this.note = note;
        this.isCancelled = isCancelled;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPriceAtOrder() {
        return unitPriceAtOrder;
    }
    public void setUnitPriceAtOrder(double unitPriceAtOrder) {
        this.unitPriceAtOrder = unitPriceAtOrder;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
