package restaurant.infrastructure.event;

import restaurant.domain.entity.Order;
import restaurant.domain.entity.Table;
import restaurant.domain.enums.OrderStatus;
import restaurant.domain.enums.TableStatus;
import restaurant.infrastructure.repository.OrderRepository;
import restaurant.infrastructure.repository.TableRepository;

public class PaymentEvent implements Event {
    private Order order;
    private OrderRepository orderRepository;
    private TableRepository tableRepository;

    public PaymentEvent(Order order, OrderRepository orderRepository, TableRepository tableRepository) {
        this.order = order;
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public void fireEvent() {
        System.out.println("Khách đã ăn xong");
        System.out.println("-> Khách hàng tại bàn ID [" + order.getTableId() + "] yêu cầu thanh toán hóa đơn.");

        if (order != null && orderRepository != null && tableRepository != null) {

            // 1. Cập nhật trạng thái đơn hàng thành COMPLETED (Hoàn thành)
            order.setOrderStatus(OrderStatus.COMPLETED);
            System.out.println("=> Thanh toán thành công! Trạng thái đơn hàng đã chuyển thành: COMPLETED");

            // 2. Tìm bàn ăn tương ứng để giải phóng
            Table table = tableRepository.findById(order.getTableId());
            if (table != null) {
                // Chuyển trạng thái bàn về EMPTY (Trống) để đón lượt khách tiếp theo trong hàng đợi
                table.setStatus(TableStatus.EMPTY);
                System.out.println("=> Bàn ID [" + table.getId() + "] đã được dọn dẹp và chuyển trạng thái về: EMPTY (Trống). Sẵn sàng phục vụ khách tiếp theo!");
            } else {
                System.out.println("-> Cảnh báo: Không tìm thấy bàn tương ứng với đơn hàng để cập nhật trạng thái!");
            }

        } else {
            System.out.println("-> Lỗi: Thiếu thông tin Order, OrderRepository hoặc TableRepository!");
        }
    }
}