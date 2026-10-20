package restaurant.infrastructure.event;
import  java.math.BigDecimal;
import java.time.LocalDateTime;
import restaurant.domain.entity.*;
import restaurant.domain.enums.*;
import restaurant.infrastructure.repository.*;

import java.time.LocalDateTime;

public class PaymentEvent implements Event {
    private Order order;
    private OrderRepository orderRepository;
    private TableRepository tableRepository;
    private PaymentRepository paymentRepository;
    private Customer customer;

    public PaymentEvent(Order order, PaymentRepository paymentRepository, OrderRepository orderRepository, TableRepository tableRepository, Customer customer) {
        this.order = order;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.customer=customer;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public void fireEvent() {

        if (order != null && paymentRepository != null && orderRepository != null && tableRepository != null) {
            System.out.println("Khách đã ăn xong");
            System.out.println("Khách hàng tại bàn ID [" + order.getTableId() + "] yêu cầu thanh toán hóa đơn.");
            Payment payment = paymentRepository.findById(order.getOrderId(), Payment::getPaymentId);
            // 1. Tính tổng tiền từ danh sách các món trong Order
            double totalAmount = 0.0;
            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    totalAmount += item.getQuantity() * item.getUnitPriceAtOrder();
                }
            }
            // 2. Tạo mã Payment mới và tạo đối tượng Payment
            double discountAmount = 0.0; // Khai báo số tiền giảm giá
            if (customer != null && customer.getType() == CustomerType.VIP) {
                discountAmount = totalAmount * 0.10; // Giảm 10% cho khách VIP
                System.out.println("=> Khách hàng VIP [" + customer.getName() + "] được ưu đãi giảm 10%: -" + (long)discountAmount + " VNĐ");
            }
            double finalAmount = totalAmount - discountAmount; // Khai báo số tiền thực trả
            int newPaymentId = paymentRepository.findAll().size() + 1;
            Payment payment1 = new Payment(
                    newPaymentId,
                    order.getOrderId(),
                    0,
                    totalAmount,
                    discountAmount,
                    finalAmount,
                    PaymentMethod.CASH, //Phương thức mặt định là trả tiền mặt
                    LocalDateTime.now(),
                    PaymentStatus.SUCCESS
            );
            // 3. Chuyển trạng thái thanh toán sang SUCCESS và lưu vào kho dữ liệu
            payment1.setStatus(PaymentStatus.SUCCESS);
            paymentRepository.add(payment);
            // 4. Cập nhật trạng thái đơn hàng thành COMPLETED
            order.setOrderStatus(OrderStatus.COMPLETED);
            System.out.println("---- Thanh toán đơn ID [" + payment1.getOrderId() + "] bằng hình thức " + payment1.getMethod() + " ----");
            System.out.println("=> Số tiền: " + (long)finalAmount + " VNĐ");
            System.out.println("=> Thanh toán thành công!!!");

            // 5. Tìm bàn ăn tương ứng để giải phóng
            Table table = tableRepository.findById(order.getTableId());
            if (table != null) {
                // Chuyển trạng thái bàn về EMPTY để đón lượt khách tiếp theo
                table.setStatus(TableStatus.EMPTY);
                System.out.println("=> Bàn ID [" + table.getId() + "] đã được dọn dẹp");
                System.out.println("Sẵn sàng phục vụ khách tiếp theo!!!");
            } else {
                System.out.println(" Cảnh báo: Không tìm thấy bàn tương ứng với đơn hàng để cập nhật trạng thái!");
            }
        }
        else {
            System.out.println(" Lỗi: Sự kiện chưa được cung cấp đủ dữ liệu!");
        }
    }
}