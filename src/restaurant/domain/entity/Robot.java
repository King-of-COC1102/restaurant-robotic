package restaurant.domain.entity;

import restaurant.domain.enums.*;
import restaurant.infrastructure.event.*;

import java.util.List;

public class Robot {
    private int robotId;
    private String name;
    private RoborType type;
    private RobotStatus status;
    private int batteryLevel;
    private int quantity;
    public static List<Event> eventList;


    public Robot(int robotId, String name, RoborType type, RobotStatus status, int batteryLevel,int quantity) {
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

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
