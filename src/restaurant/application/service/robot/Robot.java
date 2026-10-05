package restaurant.application.service.robot;

import restaurant.domain.enums.RoborType;
import restaurant.domain.enums.RobotStatus;

public class Robot {
    int robotId;
    String name;
    RoborType type;
    RobotStatus status;
    int batteryLevel;

    public Robot(int robotId, String name, RoborType type, RobotStatus status, int batteryLevel) {
        this.robotId = robotId;
        this.name = name;
        this.type = type;
        this.status = status;
        this.batteryLevel = batteryLevel;
    }

    public int getRobotId() {
        return robotId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public RoborType getType() {
        return type;
    }
    public void setType(RoborType type) {
        this.type = type;
    }

    public RobotStatus getStatus() {
        return status;
    }
    public void setStatus(RobotStatus status) {
        this.status = status;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}
