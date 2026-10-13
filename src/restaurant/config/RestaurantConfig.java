package restaurant.config;

import restaurant.domain.entity.Robot;
import restaurant.infrastructure.event.*;

public class RestaurantConfig {
    OrderCreatedEvent orderEvent = new OrderCreatedEvent();
    OrderStatusChangedEvent changedEvent = new OrderStatusChangedEvent();
    CustomerArrivedEvent arrivedEvent = new CustomerArrivedEvent();
    PaymentRequestdEvent paymentEvent = new PaymentRequestdEvent();
    SupportRequestedEvent supportEvent = new SupportRequestedEvent();
    NoRobotAvailableEvent noRobotEvent = new NoRobotAvailableEvent();

}
