package restaurant.infrastructure.repository;

import restaurant.domain.entity.Order;
import restaurant.domain.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    List<Order> list = new ArrayList<>();

    public void addOrder(Order order)
    {
        list.add(order);
    }
    // find order by Id
    public Order findById(String id)
    {
        for (var order : list)
        {
            if (order.getOrderId().equalsIgnoreCase(id))
            {
                return order;
            }
        }
        return null;
    }
    // return copy list order
    public List<Order> findAll()
    {
        return new ArrayList<>(list);
    }
    // find status of the order
    public OrderStatus findByStatus(OrderStatus status)
    {
        for (var order : list) {
            if (order.getStatus() == status) {
                return order.getStatus();
            }
        }
        return null;
    }
    // update order if guest want to change dish
}
