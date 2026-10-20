package restaurant.application.robot;

import restaurant.domain.entity.Robot;
import restaurant.domain.enums.*;


public class OrderTakingRobot extends Robot {
    public OrderTakingRobot(int robotId, String name, RobotType type, RobotStatus status, int batteryLevel, int quantity)
    {
        super(robotId, name, type, status, batteryLevel, quantity);
    }

}
