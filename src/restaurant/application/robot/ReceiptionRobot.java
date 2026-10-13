package restaurant.application.robot;

import restaurant.domain.entity.Robot;
import restaurant.domain.enums.RoborType;
import restaurant.domain.enums.RobotStatus;

public class ReceiptionRobot extends Robot {
    ReceiptionRobot(int robotId,
                 String name,
                 RoborType type,
                 RobotStatus status,
                 int batteryLevel)
    {
        super(robotId, name, type, status, batteryLevel);
    }
}
