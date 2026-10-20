package restaurant.app;

import restaurant.config.RestaurantConfig;
import restaurant.domain.entity.*;
import restaurant.domain.enums.*;
import restaurant.infrastructure.event.*;
import restaurant.infrastructure.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("     HỆ THỐNG QUẢN LÝ NHÀ HÀNG THÔNG MINH (SMART RESTAURANT SYSTEM)    ");
        System.out.println("======================================================================\n");

        // 1. KHỞI TẠO CẤU HÌNH TỪ RESTAURANTCONFIG
        System.out.println("➡️ 1. KHỞI TẠO CẤU HÌNH NHÀ HÀNG TỪ CONFIG...");
        RestaurantConfig config = new RestaurantConfig();
        TableRepository tableRepo = config.getTableRepository();
        DishRepository dishRepo = config.getDishRepository();
        RobotRepository robotRepo = config.getRobotRepository();
        OrderRepository orderRepo = config.getOrderRepository();
        PaymentRepository paymentRepo = config.getPaymentRepository();
        System.out.println("-> Hệ thống đã sẵn sàng hoạt động.\n");

        // 2. MÔ PHỎNG 1 VÒNG ĐỜI SỰ KIỆN CỦA KHÁCH HÀNG
        System.out.println("➡️ 2. BẮT ĐẦU MÔ PHỎNG VÒNG ĐỜI SỰ KIỆN (EVENT-DRIVEN)...");

        // Sự kiện 1: Khách đến nhà hàng
        System.out.println("\n--- [🎁 SỰ KIỆN 1: CustomerArrivedEvent] ---");
        Customer customer = new Customer(10, "Tran Van Binh", "0933221100", CustomerType.REGULAR, LocalDateTime.now());
        CustomerArrivedEvent arrivedEvent = new CustomerArrivedEvent(customer, tableRepo);
        arrivedEvent.fireEvent();

        // Sự kiện 2: Đặt món
        System.out.println("\n--- [🎁 SỰ KIỆN 2: OrderCreatedEvent] ---");
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(101, 10, 1, 2, 65000.0, "Ăn tại chỗ", false));
        orderItems.add(new OrderItem(102, 10, 4, 2, 15000.0, "Uống lạnh", false));

        Order order = new Order(10, 1, orderItems, customer.getCustomerId(), OrderStatus.PREPARING, null);
        OrderCreatedEvent orderEvent = new OrderCreatedEvent(order, orderRepo, robotRepo, dishRepo, customer);
        orderEvent.fireEvent();

        // Sự kiện 3: Bếp nấu và bưng món ra bàn
        System.out.println("\n--- [🎁 SỰ KIỆN 3: KitchenFinishedEvent] ---");
        KitchenFinishedEvent kitchenEvent = new KitchenFinishedEvent(order, robotRepo);
        kitchenEvent.fireEvent();

        // Sự kiện 4: Thanh toán và giải phóng bàn (Đúng 3 tham số theo constructor mã nguồn)
        System.out.println("\n--- [🎁 SỰ KIỆN 4: PaymentEvent] ---");
        PaymentEvent paymentEvent = new PaymentEvent(order, paymentRepo,orderRepo, tableRepo, customer);
        paymentEvent.fireEvent();

        System.out.println("\n-> Hoàn tất mô phỏng chuỗi sự kiện thành công!\n");

        // 3. GỌI BÁO CÁO 15 CÂU TRUY VẤN
        System.out.println("======================================================================\n");
        System.out.println("➡️ 3. XUẤT BÁO CÁO 15 CÂU TRUY VẤN DỮ LIỆU TỪ SAMPLEDATA\n");
        SampleData.main(args);
    }
}