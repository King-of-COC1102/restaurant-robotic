package restaurant.infrastructure.event;

import restaurant.domain.entity.*;
import restaurant.domain.enums.*;
import restaurant.infrastructure.repository.*;

public class PaymentEvent implements Event {
    private Order order;
    private OrderRepository orderRepository;
    private TableRepository tableRepository;
    private PaymentRepository paymentRepository;

    public PaymentEvent(Order order,
                        PaymentRepository paymentRepository,
                        OrderRepository orderRepository,
                        TableRepository tableRepository) {
        this.order = order;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public void fireEvent() {

        if (order != null && paymentRepository != null  && orderRepository != null && tableRepository != null) {
            System.out.println("Khách đã ăn xong");
            System.out.println("Khách hàng tại bàn ID [" + order.getTableId() + "] yêu cầu thanh toán hóa đơn.");
            Payment payment =  paymentRepository.findById(order.getOrderId());
            // Cập nhật trạng thái đơn hàng thành PEDNDING (Đang thanh toán)
            payment.setStatus(PaymentStatus.PENDING);
            // Cập nhật trạng thái đơn hàng thành success (thanh toán thành công)
            payment.setStatus(PaymentStatus.SUCCESS);
            System.out.println("---- Thanh toán đơn ID [" + payment.getOrderId() + "] bằng " + payment.getMethod() + "----");
            System.out.println("=> Thanh toán thành công!!!");

            // Tìm bàn ăn tương ứng để giải phóng
            Table table = tableRepository.findById(order.getTableId());
            if (table != null) {
                // Chuyển trạng thái bàn về EMPTY (Trống) để đón lượt khách tiếp theo trong hàng đợi
                table.setStatus(TableStatus.EMPTY);
                System.out.println("=> Bàn ID [" + table.getId() + "] đã được dọn dẹp");
                System.out.println("Sẵn sàng phục vụ khách tiếp theo!!!");
            } else {
                System.out.println(" Cảnh báo: Không tìm thấy bàn tương ứng với đơn hàng để cập nhật trạng thái!");
            }

        } else {
            System.out.println(" Lỗi: Sự kiện chưa được cung cấp đủ dữ liệu!");
        }
    }
}