package restaurant.application.service.robot;

import restaurant.domain.enums.RoborType;
import restaurant.domain.enums.RobotStatus;

public class ServingRobot extends Robot{
    ServingRobot(int robotId,
                 String name,
                 RoborType type,
                 RobotStatus status,
                 int batteryLevel)
    {
        super(robotId, name, type, status, batteryLevel);
    }
}
