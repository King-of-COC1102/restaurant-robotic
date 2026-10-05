package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.Category;
import restaurant.domain.entity.Order;
import restaurant.domain.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends Repository<Order, Integer>{
    public OrderRepository(List<Category> list)
    {
        super(list);
    }
    public Order findById(int id)
    {
        return findById(id, Order::getOrderId);
    }
    public List<Order> findByStatus(OrderStatus status)
    {
        return findBy(o->o.getStatus() == status);
    }
}
