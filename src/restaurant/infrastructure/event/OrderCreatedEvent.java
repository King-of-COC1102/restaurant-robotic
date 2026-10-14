package restaurant.infrastructure.event;
import restaurant.application.robot.OrderTakingRobot;
import restaurant.domain.entity.Order;
import restaurant.domain.entity.OrderItem;
import restaurant.domain.entity.Dish;
import restaurant.domain.entity.Robot;
import restaurant.domain.enums.RoborType;
import restaurant.domain.enums.OrderStatus;
import restaurant.domain.enums.RobotStatus;
import restaurant.infrastructure.repository.OrderRepository;
import restaurant.infrastructure.repository.RobotRepository;
import restaurant.infrastructure.repository.DishRepository;
import restaurant.domain.entity.Customer;

import java.util.List;
public class OrderCreatedEvent implements Event{

    private Order order;
    private OrderRepository orderRepository;
    private RobotRepository robotRepository;
    private DishRepository dishRepository;
    private Customer customer;
    public OrderCreatedEvent(Order order,OrderRepository orderRepository,RobotRepository robotRepository,DishRepository dishRepository,Customer customer) {
        this.order=order;
        this.orderRepository=orderRepository;
        this.robotRepository=robotRepository;
        this.dishRepository=dishRepository;
        this.customer=customer;
    }
    public Order getOrder() {
        return order;
    }
    @Override
    public void fireEvent()
    {
        System.out.println("Đang check robot để tiếp khách "+customer.getName());
        System.out.println(".......");
        if(orderRepository!=null) { //Chắc chắn kho không bao giờ trống
            if(robotRepository!=null) {
                List<Robot> availableRobots=robotRepository.findAvailableRobot(RoborType.ORDER_TAKING,RobotStatus.AVAILABLE);
                if(availableRobots.isEmpty()) { //CHECK LẠI XEM CÓ THỂ XỬ LÍ EVENT TIẾP KO!!!!
                    System.out.println("Mong quý khách thông cảm! Do lưu lượng khác nhà hàng lớn. Quý khác vui lòng đợi trong giây lát 😅!");
                }
                else {
                    Robot availableRobot=availableRobots.get(0);
                    System.out.println("->Robot ["+ availableRobot.getRobotId()+"] "+availableRobot.getName()+" sẽ phục vụ quý khách");
                    availableRobot.setStatus(RobotStatus.BUSY);
                    if(dishRepository != null && order!=null && orderRepository!=null && order.getItems()!=null) {
                        boolean isAllDishesAvailable=true; //Kiểm tra tính hợp lệ của đơn khách đặt
                        //Duyệt từng món khách gọi
                        for (OrderItem item: order.getItems()) {
                            Dish dish= dishRepository.findById(item.getDishId());
                            if(dish==null) { //SET TẠM THÔI
                                isAllDishesAvailable=false;
                                System.out.println("->Món ăn ["+item.getDishId()+"] "+dish.getName()+" hiện tại đã HẾT!");
                                break;
                            }
                        }
                        //NẾU MÓN CÒN ĐỦ
                        if (isAllDishesAvailable) {
                            order.setOrderStatus(OrderStatus.PREPARING);
                            orderRepository.add(order);
                            System.out.println("=> Đặt đơn hàng thành công! Trạng thái: Đang chuẩn bị");
                            System.out.println("---XÁC NHẬN ĐẶT MÓN---");
                            for (OrderItem item: order.getItems()) {
                                Dish dish= dishRepository.findById(item.getDishId());
                                System.out.println("->Món ["+item.getDishId()+"] "+dish.getName());
                                System.out.println("  Số lượng món: "+ item.getQuantity());
                            }
                            availableRobot.setStatus(RobotStatus.AVAILABLE);
                        }
                        //NẾU MÓN HẾT
                        else {
                            System.out.println("[CƠ CHẾ ĐẶT LẠI] Yêu cầu quý khách chọn lại món");
                            order.getItems().clear();
                            order.setOrderStatus(OrderStatus.CANCELLED);
                            availableRobot.setStatus(RobotStatus.AVAILABLE);

                        }
                    }
                    else {
                        System.out.println("Lỗi: Thiếu khởi tạo Repository (dishRepository/orderRepository) hoặc đơn hàng trống!");
                    }
                }
            }
        }
    }
    //Robot có rãnh, nếu rãnh thì order vào bếp
}
