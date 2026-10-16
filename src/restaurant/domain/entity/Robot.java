package restaurant.domain.entity;

import restaurant.domain.enums.*;
import restaurant.infrastructure.event.*;

import java.util.List;

public class Robot {
    protected int robotId;
    protected String name;
    protected RobotType type;
    protected RobotStatus status;
    protected int batteryLevel;
    protected int quantity;
    protected static List<Event> eventList;


    public Robot(int robotId, String name, RobotType type, RobotStatus status, int batteryLevel,int quantity) {
        this.robotId = robotId;
        this.name = name;
        this.type = type;
        this.status = status;
        this.batteryLevel = batteryLevel;
        this.quantity=quantity;
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

    public RobotType getType() {
        return type;
    }
    public void setType(RobotType type) {
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

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
