package restaurant.infrastructure.repository;

import restaurant.domain.entity.Robot;
import restaurant.application.robot.*;
import restaurant.domain.enums.*;

import java.util.ArrayList;
import java.util.List;
public class RobotRepository extends Repository<Robot, Integer>{

    public RobotRepository(List<Robot> list) {
        super(list);
    }
    //Đếm số lượng Robot của 1 loại cụ thể
    public int countRobotType(RobotType type) {
        int count=0;
        for (Robot robot: list) {
            if(robot.getType()==type){
                count++;
            }
        }
        return count;
    }
    public List<Robot> findByType(RobotType type)
    {
        return findBy(r -> r.getType() == type);
    }

    //Đếm xem có bao nhiêu con Robot rảnh(AVAILABLE) của 1 loại
    public int countAvailableRobotType(RobotType type) {
        int count=0;
        for (Robot robot: list) {
            if (robot.getType()==type && robot.getStatus()==RobotStatus.AVAILABLE) {
                count++;
            }
        }
        return count;
    }

    // tìm robot có trạng thái available
    public List<Robot> findAvailableRobot (RobotType type, RobotStatus status) {
        return findBy(r -> r.getType() == type && r.getStatus() == status);
    }
}
