package restaurant.infrastructure.event;
import restaurant.domain.entity.Order;
import restaurant.domain.entity.Robot;
import restaurant.domain.enums.OrderStatus;
import restaurant.domain.enums.RobotStatus;
import restaurant.domain.enums.RoborType;

import java.util.List;
import restaurant.infrastructure.repository.RobotRepository;
public class KitchenFinishedEvent implements Event{
    private Order order;
    private RobotRepository robotRepository;
    public KitchenFinishedEvent(Order order,RobotRepository robotRepository) {
        this.order=order;
        this.robotRepository=robotRepository;
    }
    public Order getOrder() {
        return order;
    }

    @Override
    public void fireEvent()
    {
        System.out.println("-> Đơn hàng của bàn ID ["+order.getTableId()+"] đã được chuyển xuống bếp");
        if(robotRepository!=null && order!=null) {
            List<Robot> kitchenRobots=robotRepository.findAvailableRobot(RoborType.KITCHEN,RobotStatus.AVAILABLE);
            if(kitchenRobots.isEmpty()) { //---> Việc chọn con rãnh sẽ THÔNG QUA SAMPLE DATA đưa ra trạng thái của từng con
                System.out.println("->Nhà bếp đang quá tải! Robot nấu ăn đang bận nấu các bàn khác. Mong quý khách thông cảm!");
            }
            else {
                Robot kitchenRobot=kitchenRobots.get(0);
                System.out.println("Robot ["+kitchenRobot.getRobotId()+"] "+kitchenRobot.getName()+" đang nấu ăn cho bạn");
                kitchenRobot.setStatus(RobotStatus.BUSY);
                order.setOrderStatus(OrderStatus.PREPARING);
                // Giả lập quá trình nhà bếp đang nấu...
                System.out.println("... Đang nấu các món ăn cho khách ...");

                // 2. Nấu xong: Cập nhật trạng thái đơn hàng và giải phóng KitchenRobot
                order.setOrderStatus(OrderStatus.READY_TOBRING);
                kitchenRobot.setStatus(RobotStatus.AVAILABLE);

                System.out.println("=> KitchenRobot đã nấu xong! Chuyển giao món ăn cho ServingRobot để mang ra bàn.");
                //ROBOT SERVING đem đồ ăn lên bàn
                List<Robot> servingRobots = robotRepository.findAvailableRobot(RoborType.SERVING, RobotStatus.AVAILABLE);

                if (servingRobots.isEmpty()) {
                    System.out.println("-> Hiện tại tất cả ServingRobot đang bận bưng bê bàn khác. Đợi một lát sẽ có robot phục vụ!");
                } else {
                    Robot servingRobot = servingRobots.get(0);
                    System.out.println("Robot [" + servingRobot.getRobotId() + "] " + servingRobot.getName() + " nhận món và đang di chuyển mang ra bàn ID: " + order.getTableId());

                    // Chuyển trạng thái ServingRobot thành bận
                    servingRobot.setStatus(RobotStatus.BUSY);

                    // Giả lập quá trình mang ra bàn hoàn tất
                    System.out.println("=> Món ăn đã được ServingRobot dọn lên bàn thành công!");

                    // Phục vụ xong, trả ServingRobot về trạng thái rảnh
                    servingRobot.setStatus(RobotStatus.AVAILABLE);
                }
            }
        }
        else {
            System.out.println("->Lỗi: RobotRepository hoặc order trống");
        }
    }
}
