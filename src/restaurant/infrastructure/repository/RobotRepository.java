package restaurant.infrastructure.repository;

import restaurant.domain.entity.Robot;
import restaurant.application.robot.*;
import restaurant.domain.enums.RoborType;
import restaurant.domain.enums.RobotStatus;
import java.util.ArrayList;
import java.util.List;
public class RobotRepository extends Repository<Robot, String>{

    public RobotRepository(List<Robot> list) {
        super(list);
    }
    //Đếm số lượng Robot của 1 loại cụ thể
    public int countRobotType(RoborType type) {
        int count=0;
        for (Robot robot: list) {
            if(robot.getType()==type){
                count++;
            }
        }
        return count;
    }
    //Đếm xem có bao nhiêu con Robot rảnh(AVAILABLE) của 1 loại
    public int countAvailableRobotType(RoborType type) {
        int count=0;
        for (Robot robot: list) {
            if (robot.getType()==type && robot.getStatus()==RobotStatus.AVAILABLE) {
                count++;
            }
        }
        return count;
    }
    public List<Robot> findAvailableRobot (RoborType type,RobotStatus status) {
        List<Robot> result = new ArrayList<>();
        for (Robot robot:list) {
            if(robot.getType()==type && robot.getStatus()==status ) {
                result.add(robot);
            }
        }
        return result;
    }
}
