package restaurant.application.service;

import restaurant.domain.entity.*;
import restaurant.domain.enums.*;
import restaurant.infrastructure.repository.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public class RestaurantAnalyticsService {
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final TableRepository tableRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final RobotRepository robotRepository;


    RestaurantAnalyticsService(OrderRepository orderRepository,
                               DishRepository dishRepository,
                               TableRepository tableRepository,
                               PaymentRepository paymentRepository,
                               CustomerRepository customerRepository,
                               RobotRepository robotRepository)
    {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.paymentRepository = paymentRepository;
        this.tableRepository = tableRepository;
        this.customerRepository = customerRepository;
        this.robotRepository = robotRepository;
    }

    // Câu 16: In top N món bán chạy nhất
    public void printTopNBestSellers(int n) {
        List<Map.Entry<Integer, Integer>> topN = orderRepository.topNSoldByDish(n);
        if (topN.isEmpty()) {
            System.out.println("Không có dữ liệu món bán chạy (chưa bán món nào hoặc n <= 0).");
            return;
        }

        System.out.println("===== TOP " + n + " MÓN BÁN CHẠY NHẤT =====");
        System.out.printf("%-5s %-5s %-25s %s%n", "Hạng", "ID", "Tên món", "Số phần");
        System.out.println("-".repeat(50));

        for (int i = 0; i < topN.size(); i++) {
            Map.Entry<Integer, Integer> e = topN.get(i);
            Dish dish = dishRepository.findById(e.getKey());
            String name = (dish != null) ? dish.getName() : "(không rõ)";
            System.out.printf("%-5d %-5d %-25s %d%n", i + 1, e.getKey(), name, e.getValue());
        }
    }

    // Câu 18: Số robot và pin trung bình theo loại
    public void printRobotStatsByType() {
        System.out.println("===== SỐ ROBOT VÀ PIN TRUNG BÌNH THEO LOẠI =====");
        System.out.printf("%-15s %-10s %s%n", "Loại robot", "Số robot", "Pin trung bình");
        System.out.println("-".repeat(40));

        for (RobotType type : RobotType.values()) {
            List<Robot> robots = robotRepository.findByType(type);
            int totalBattery = 0;
            for (Robot r : robots) {
                totalBattery += r.getBatteryLevel();
            }
            String avg = robots.isEmpty() ? "-" : String.format("%.1f%%", (double) totalBattery / robots.size());

            System.out.printf("%-15s %-10d %s%n", type, robots.size(), avg);
        }
    }

    // Câu 19: Khách hàng thân thiết (từ 2 phiếu trở lên)
    public void printReturningCustomers() {
        System.out.println("===== KHÁCH HÀNG THÂN THIẾT (TỪ 2 PHIẾU TRỞ LÊN) =====");
        System.out.printf("%-5s %-20s %-10s %-8s %s%n", "ID", "Tên khách", "Loại", "Số phiếu", "Tổng chi tiêu");
        System.out.println("-".repeat(65));

        boolean found = false;

        for (Customer c : customerRepository.findAll()) {
            List<Order> orders = orderRepository.findByCustomerId(c.getCustomerId());   // phiếu của khách này

            if (orders.size() >= 2) {                                             // lọc: khách quay lại
                double spent = 0;
                for (Order o : orders) {
                    Payment p = paymentRepository.findByOrderId(o.getOrderId());
                    if (p != null && p.getStatus() == PaymentStatus.SUCCESS) {
                        spent += p.getFinalAmount();                              // cộng tiền đã trả
                    }
                }
                System.out.printf("%-5d %-20s %-10s %-8d %.0f%n",
                        c.getCustomerId(), c.getName(), c.getType(), orders.size(), spent);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có khách nào quay lại.");
        }
    }

    // Câu 20: Doanh thu theo bàn
    public void printRevenueByTable() {
        List<Table> tables = tableRepository.findAll();
        if (tables.isEmpty()) {
            System.out.println("Chưa có bàn nào.");
            return;
        }

        System.out.println("===== DOANH THU THEO BÀN =====");
        System.out.printf("%-8s %-10s %s%n", "Bàn", "Sức chứa", "Doanh thu");
        System.out.println("-".repeat(35));

        for (Table t : tables) {
            double revenue = 0;
            for (Order o : orderRepository.findByTableId(t.getId())) {
                Payment p = paymentRepository.findByOrderId(o.getOrderId());
                if (p != null && p.getStatus() == PaymentStatus.SUCCESS) {
                    revenue += p.getFinalAmount();
                }
            }
            System.out.printf("%-8d %-10d %.0f%n", t.getId(), t.getCapacity(), revenue);
        }
    }

    // Câu 22: Tổng số lượng bán của từng món trong các order hoàn thành
    public void printTotalDishSold() {
        Map<Integer, Integer> map = orderRepository.totalDishSold();
        if (map.isEmpty()) {
            System.out.println("Chưa có món nào được bán trong các order hoàn thành.");
            return;
        }
        System.out.println("===== TỔNG SỐ LƯỢNG BÁN CỦA TỪNG MÓN =====");
        System.out.printf("%-5s %-25s %s%n", "ID", "Tên món", "Số lượng");
        System.out.println("-".repeat(45));

        map.forEach((dishId, quantity) -> {
            Dish dish = dishRepository.findById(dishId);
            String name = (dish != null) ? dish.getName() : "(không rõ)";
            System.out.printf("%-5d %-25s %d%n", dishId, name, quantity);
        });
    }

    // Câu 24: Bàn có ít nhất 2 order hoàn thành
    public void printTablesWithAtLeastTwoCompletedOrders() {
        Map<Integer, Long> map = orderRepository.atLeastTwoOrderDone();
        if (map.isEmpty()) {
            System.out.println("Không có bàn nào có từ 2 order hoàn thành trở lên.");
            return;
        }
        System.out.println("===== BÀN CÓ ÍT NHẤT 2 ORDER HOÀN THÀNH =====");
        System.out.printf("%-8s %s%n", "Bàn", "Số order");
        System.out.println("-".repeat(25));

        map.forEach((tableId, count) ->
                System.out.printf("%-8d %d%n", tableId, count));
    }

    // Câu 25: Top N phiếu thanh toán có số tiền cao nhất
    public void printTopNPayments(int n) {
        List<Payment> payments = paymentRepository.TopNPayments(n);
        if (payments.isEmpty()) {
            System.out.println("Chưa có phiếu thanh toán thành công nào.");
            return;
        }
        System.out.println("===== TOP " + n + " PHIẾU THANH TOÁN CAO NHẤT =====");
        System.out.printf("%-6s %-8s %-12s %-15s %s%n", "Mã", "Order", "Phương thức", "Thành tiền", "Thời gian");
        System.out.println("-".repeat(65));

        payments.forEach(p ->
                System.out.printf("%-6d %-8d %-12s %-15.0f %s%n",
                        p.getPaymentId(),
                        p.getOrderId(),
                        p.getMethod(),
                        p.getFinalAmount(),
                        p.getPaidAt()));
    }

    // Câu 26: Số khách và doanh thu theo loại khách
    public void printCustomerAndRevenueByType() {
        System.out.println("===== SỐ KHÁCH VÀ DOANH THU THEO LOẠI KHÁCH =====");
        System.out.printf("%-12s %-10s %s%n", "Loại khách", "Số khách", "Doanh thu");
        System.out.println("-".repeat(40));

        for (CustomerType type : CustomerType.values()) {
            List<Customer> customers = customerRepository.findByType(type);
            double revenue = 0;

            for (Customer c : customers) {
                for (Order o : orderRepository.findByCustomerId(c.getCustomerId())) {
                    Payment p = paymentRepository.findByOrderId(o.getOrderId());
                    if (p != null && p.getStatus() == PaymentStatus.SUCCESS) {
                        revenue += p.getFinalAmount();
                    }
                }
            }

            System.out.printf("%-12s %-10d %.0f%n", type, customers.size(), revenue);
        }
    }

    // Câu 27: Các order hoàn thành trong từng tháng
    public void printCompletedOrdersByMonth() {
        Map<YearMonth, List<Order>> map = orderRepository.completedOrdersByMonth();
        if (map.isEmpty()) {
            System.out.println("Chưa có order nào hoàn thành.");
            return;
        }
        System.out.println("===== ORDER HOÀN THÀNH THEO TỪNG THÁNG =====");

        map.forEach((month, orders) -> {
            System.out.println("Tháng " + month.getMonthValue() + "/" + month.getYear()
                    + " (" + orders.size() + " order)");
            System.out.printf("  %-8s %-8s %s%n", "Order", "Bàn", "Thời gian");
            orders.forEach(o ->
                    System.out.printf("  %-8d %-8d %s%n",
                            o.getOrderId(), o.getTableId(), o.getPaymentTime()));
            System.out.println();
        });
    }

    // Câu 29: Số order theo khung giờ trong ngày
    public void printOrdersByHour() {
        Map<Integer, Long> map = orderRepository.countOrdersByHour();
        if (map.isEmpty()) {
            System.out.println("Chưa có order nào.");
            return;
        }
        System.out.println("===== SỐ ORDER THEO KHUNG GIỜ =====");
        System.out.printf("%-15s %s%n", "Khung giờ", "Số order");
        System.out.println("-".repeat(30));

        map.forEach((hour, count) ->
                System.out.printf("%02d:00 - %02d:59   %d%n", hour, hour, count));
    }

    // Câu 30: Phiếu thanh toán có giá trị trên mức trung bình
    public void printPaymentsAboveAverage() {
        double avg = paymentRepository.averagePaidAmount();
        List<Payment> payments = paymentRepository.paymentsAboveAverage();

        if (payments.isEmpty()) {
            System.out.println("Không có phiếu thanh toán nào trên mức trung bình.");
            return;
        }
        System.out.println("===== PHIẾU THANH TOÁN TRÊN MỨC TRUNG BÌNH =====");
        System.out.printf("Mức trung bình: %.0f%n", avg);
        System.out.printf("%-6s %-8s %-12s %s%n", "Mã", "Order", "Phương thức", "Thành tiền");
        System.out.println("-".repeat(45));

        payments.forEach(p ->
                System.out.printf("%-6d %-8d %-12s %.0f%n",
                        p.getPaymentId(),
                        p.getOrderId(),
                        p.getMethod(),
                        p.getFinalAmount()));
    }
}
