package restaurant.application.service;

import restaurant.infrastructure.repository.DiscountRepository;
import restaurant.infrastructure.repository.DishRepository;
import restaurant.infrastructure.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final DiscountRepository discountRepository;

    public OrderService(OrderRepository orderRepository,
                 DishRepository dishRepository,
                 DiscountRepository discountRepository)
    {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.discountRepository = discountRepository;
    }

}
