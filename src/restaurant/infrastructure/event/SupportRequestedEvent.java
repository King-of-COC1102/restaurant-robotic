package restaurant.infrastructure.event;

import restaurant.domain.entity.SupportRequest;
import restaurant.domain.entity.Robot;
import restaurant.domain.enums.RobotStatus;
import restaurant.domain.enums.RoborType;
import restaurant.domain.enums.SupportRequestStatus;
import restaurant.infrastructure.repository.RobotRepository;

import java.util.List;

public class SupportRequestedEvent implements Event {
    private SupportRequest supportRequest;
    private RobotRepository robotRepository;

    public SupportRequestedEvent(SupportRequest supportRequest, RobotRepository robotRepository) {
        this.supportRequest = supportRequest;
        this.robotRepository = robotRepository;
    }

    public SupportRequest getSupportRequest() {
        return supportRequest;
    }

    @Override
    public void fireEvent() {
        System.out.println("-> [YÊU CẦU HỖ TRỢ] Bàn ID [" + supportRequest.getTableId() + "] đang cần giúp đỡ!");

        if (supportRequest != null && robotRepository != null) {
            // Cập nhật trạng thái yêu cầu
            supportRequest.setStatus(SupportRequestStatus.HANDLING); // Đang chờ xử lý

            // Tìm SupportRobot đang rảnh
            List<Robot> supportRobots = robotRepository.findAvailableRobot(RoborType.SUPPORT, RobotStatus.AVAILABLE);

            if (supportRobots.isEmpty()) {
                System.out.println("-> Hiện tại tất cả SupportRobot đang bận xử lý các yêu cầu khác. Vui lòng đợi trong giây lát!");
            } else {
                Robot supportRobot = supportRobots.get(0);
                System.out.println("-> SupportRobot [" + supportRobot.getRobotId() + "] " + supportRobot.getName() + " đang di chuyển đến bàn ID: " + supportRequest.getTableId());

                // Đổi trạng thái robot thành bận
                supportRobot.setStatus(RobotStatus.BUSY);

                // Giả lập quá trình hỗ trợ hoàn tất
                System.out.println("=> SupportRobot đã đến nơi và hỗ trợ khách hàng thành công!");

                // Cập nhật trạng thái yêu cầu đã hoàn thành
                supportRequest.setStatus(SupportRequestStatus.HANDLING);

                // Trả robot về trạng thái rảnh
                supportRobot.setStatus(RobotStatus.AVAILABLE);
            }
        } else {
            System.out.println("-> Lỗi: SupportRequest hoặc RobotRepository trống!");
        }
    }
}