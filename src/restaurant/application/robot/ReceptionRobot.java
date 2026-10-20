package restaurant.application.robot;

import restaurant.domain.entity.Robot;
import restaurant.domain.enums.*;

public class ReceptionRobot extends Robot {
    public ReceptionRobot(int robotId, String name, RobotStatus status, int batteryLevel, int quantity)
    {
        super(robotId, name, RobotType.RECEPTION, status, batteryLevel, quantity);
    }
}
