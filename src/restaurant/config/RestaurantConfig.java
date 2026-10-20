package restaurant.config;

import restaurant.app.SampleData;
import restaurant.infrastructure.repository.*;

import java.util.ArrayList;

public class RestaurantConfig {

    private TableRepository tableRepository;
    private DishRepository dishRepository;
    private RobotRepository robotRepository;
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public RestaurantConfig() {
        this.tableRepository = new TableRepository(new ArrayList<>(SampleData.getSampleTables()));
        this.dishRepository = new DishRepository(new ArrayList<>(SampleData.getSampleDishes()));
        this.robotRepository = new RobotRepository(new ArrayList<>(SampleData.getSampleRobots()));
        this.orderRepository = new OrderRepository(new ArrayList<>(SampleData.getSampleOrders()));
        this.paymentRepository = new PaymentRepository(new ArrayList<>());
    }

    public TableRepository getTableRepository() {
        return tableRepository;
    }

    public DishRepository getDishRepository() {
        return dishRepository;
    }

    public RobotRepository getRobotRepository() {
        return robotRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public PaymentRepository getPaymentRepository() {
        return paymentRepository;
    }
}