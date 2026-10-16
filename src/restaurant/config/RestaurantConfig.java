package restaurant.config;

import restaurant.domain.entity.Robot;
import restaurant.infrastructure.event.*;

public class RestaurantConfig {
    //Đã có sẵn trong nhà hàng
    OrderCreatedEvent orderEvent = new OrderCreatedEvent();
    CustomerArrivedEvent arrivedEvent = new CustomerArrivedEvent();
    SupportRequestedEvent supportEvent = new SupportRequestedEvent();

}
