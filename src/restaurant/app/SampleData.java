package restaurant.app;
import java.util.function.*;
import restaurant.domain.entity.*;
import restaurant.domain.enums.*;
import restaurant.infrastructure.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleData {

    public static List<Table> getSampleTables() {
        return Arrays.asList(
                new Table(1, 4, TableStatus.EMPTY),
                new Table(2, 6, TableStatus.IN_USE),
                new Table(3, 4, TableStatus.RESERVED),
                new Table(4, 2, TableStatus.IN_USE),
                new Table(5, 8, TableStatus.EMPTY)
        );
    }

    public static List<Dish> getSampleDishes() {
        return Arrays.asList(
                new Dish(1, 101, "Phở Bò Đặc Biệt", 65000.0, 50, "Phở truyền thống", true),
                new Dish(2, 102, "Cơm Sườn Nướng", 45000.0, 30, "Cơm tấm sườn nướng", true),
                new Dish(3, 103, "Lẩu Thái Hải Sản", 250000.0, 15, "Lẩu chua cay hải sản", true),
                new Dish(4, 104, "Nước Ngọt Coca", 15000.0, 100, "Nước có gas", true),
                new Dish(5, 101, "Bò Wagyu Thượng Hạng", 600000.0, 0, "Thịt bò cao cấp nướng đá (Tạm hết)", false),
                new Dish(6, 105, "Gỏi Cuốn Tôm Thịt", 40000.0, 8, "Gỏi cuốn tươi (sắp hết)", true),
                new Dish(7, 106, "Chè Khúc Bạch", 20000.0, 25, "Chè khúc bạch mát lạnh", true),
                new Dish(8, 104, "Trà Đá", 5000.0, 5, "Trà đá miễn phí đá (sắp hết)", true),
                new Dish(9, 104, "Bia Tiger", 25000.0, 60, "Bia lon", true)
        );
    }

    public static List<Category> getSampleCategories() {
        return Arrays.asList(
                new Category(101, "Phở & Món Bò", "Phở và các món từ thịt bò"),
                new Category(102, "Cơm", "Các món cơm"),
                new Category(103, "Lẩu", "Các loại lẩu"),
                new Category(104, "Đồ uống", "Nước ngọt, trà, bia"),
                new Category(105, "Khai vị", "Các món khai vị"),
                new Category(106, "Tráng miệng", "Chè và món ngọt")
        );
    }

    public static List<Customer> getSampleCustomers() {
        return Arrays.asList(
                new Customer(1, "Nguyen Van An", "0901234567", CustomerType.REGULAR, LocalDateTime.now().minusMinutes(40)),
                new Customer(2, "Tran Thi Bich", "0912345678", CustomerType.VIP, LocalDateTime.now().minusMinutes(10)),
                new Customer(3, "Le Hoang Nam (Đặt trước)", "0923456789", CustomerType.VIP, LocalDateTime.now().plusMinutes(20)),
                new Customer(4, "Pham Minh Duc", "0934567890", CustomerType.REGULAR, LocalDateTime.now().minusMinutes(5)),
                new Customer(5, "Hoang Thi Lan", "0945678901", CustomerType.VIP, LocalDateTime.now().minusDays(1)),
                new Customer(6, "Vo Quoc Hung", "0956789012", CustomerType.REGULAR, LocalDateTime.now().minusDays(2)),
                new Customer(7, "Dang Thu Ha", "0967890123", CustomerType.VIP, LocalDateTime.now().minusMinutes(60)),
                new Customer(8, "Bui Gia Bao", "0978901234", CustomerType.REGULAR, LocalDateTime.now().minusDays(3))
        );
    }

    public static List<Robot> getSampleRobots() {
        return Arrays.asList(
                new Robot(1, "CookBot-01", RobotType.KITCHEN, RobotStatus.AVAILABLE, 100, 1),
                new Robot(2, "ServeBot-01", RobotType.SERVING, RobotStatus.BUSY, 80, 1),
                new Robot(3, "OrderBot-01", RobotType.ORDER_TAKING, RobotStatus.AVAILABLE, 90, 1),
                new Robot(4, "CookBot-02", RobotType.KITCHEN, RobotStatus.BUSY, 15, 1),
                new Robot(5, "ReceptBot-01", RobotType.RECEPTION, RobotStatus.AVAILABLE, 95, 1),
                new Robot(6, "SupportBot-01", RobotType.SUPPORT, RobotStatus.AVAILABLE, 70, 1)
        );
    }

    public static List<Order> getSampleOrders() {
        List<OrderItem> itemsBan2 = new ArrayList<>();
        itemsBan2.add(new OrderItem(1, 1, 1, 1, 65000.0, "Người 1: Phở không hành", false));
        itemsBan2.add(new OrderItem(2, 1, 2, 1, 45000.0, "Người 2: Cơm sườn", false));
        itemsBan2.add(new OrderItem(3, 1, 4, 3, 15000.0, "Người 3: 3 lon coca", false));

        List<OrderItem> itemsBan4 = Arrays.asList(
                new OrderItem(5, 3, 1, 1, 65000.0, "Ăn tại chỗ", false)
        );
        List<OrderItem> itemsBan5 = Arrays.asList(
                new OrderItem(4, 2, 3, 1, 250000.0, "Ít cay", false)
        );

        List<OrderItem> itemsBiHuy = Arrays.asList(
                new OrderItem(6, 4, 5, 1, 600000.0, "Khách chọn món Bò Wagyu nhưng kho đã hết", true)
        );

        Order o1 = new Order(1, 2, itemsBan2, 1, OrderStatus.PREPARING, LocalDateTime.now().minusMinutes(30));
        Order o2 = new Order(2, 5, itemsBan5, 2, OrderStatus.READY_TO_BRING, LocalDateTime.now().minusMinutes(8));
        Order o3 = new Order(3, 4, itemsBan4, 4, OrderStatus.COMPLETED, LocalDateTime.now());
        Order o4 = new Order(4, 1, itemsBiHuy, 1, OrderStatus.CANCELLED, LocalDateTime.now().minusMinutes(35));

        // ===== Order lịch sử (đã hoàn thành) - dùng cho câu 16, 19-20, 22, 24, 26, 27, 29 =====
        Order o5 = new Order(5, 1, Arrays.asList(
                new OrderItem(7, 5, 1, 2, 65000.0, "", false),
                new OrderItem(8, 5, 4, 2, 15000.0, "", false)
        ), 1, OrderStatus.COMPLETED, LocalDateTime.of(2026, 8, 15, 12, 20));

        Order o6 = new Order(6, 1, Arrays.asList(
                new OrderItem(9, 6, 3, 1, 250000.0, "Cay vừa", false),
                new OrderItem(10, 6, 2, 2, 45000.0, "", false),
                new OrderItem(11, 6, 4, 4, 15000.0, "", false)
        ), 2, OrderStatus.COMPLETED, LocalDateTime.of(2026, 8, 22, 19, 10));

        Order o7 = new Order(7, 4, Arrays.asList(
                new OrderItem(12, 7, 2, 2, 45000.0, "Thêm dưa leo", false),
                new OrderItem(13, 7, 8, 2, 5000.0, "", false)
        ), 4, OrderStatus.COMPLETED, LocalDateTime.of(2026, 9, 3, 12, 45));

        Order o8 = new Order(8, 5, Arrays.asList(
                new OrderItem(14, 8, 3, 2, 250000.0, "Không cay", false),
                new OrderItem(15, 8, 6, 2, 40000.0, "", false),
                new OrderItem(16, 8, 9, 6, 25000.0, "Bia lạnh", false)
        ), 5, OrderStatus.COMPLETED, LocalDateTime.of(2026, 9, 10, 20, 5));

        Order o9 = new Order(9, 5, Arrays.asList(
                new OrderItem(17, 9, 1, 3, 65000.0, "", false),
                new OrderItem(18, 9, 7, 3, 20000.0, "", false),
                new OrderItem(19, 9, 4, 3, 15000.0, "", false)
        ), 6, OrderStatus.COMPLETED, LocalDateTime.of(2026, 9, 18, 18, 30));

        // order 10: có 1 món bị hủy (không tính tiền, không tính vào số lượng bán)
        Order o10 = new Order(10, 3, Arrays.asList(
                new OrderItem(20, 10, 1, 2, 65000.0, "", false),
                new OrderItem(21, 10, 2, 2, 45000.0, "", false),
                new OrderItem(22, 10, 4, 1, 15000.0, "Khách đổi ý, không dùng nữa", true)
        ), 7, OrderStatus.COMPLETED, LocalDateTime.of(2026, 9, 25, 13, 15));

        Order o11 = new Order(11, 4, Arrays.asList(
                new OrderItem(23, 11, 1, 1, 65000.0, "", false),
                new OrderItem(24, 11, 2, 1, 45000.0, "", false),
                new OrderItem(25, 11, 4, 2, 15000.0, "", false)
        ), 1, OrderStatus.COMPLETED, LocalDateTime.of(2026, 10, 5, 11, 50));

        Order o12 = new Order(12, 1, Arrays.asList(
                new OrderItem(26, 12, 3, 1, 250000.0, "", false),
                new OrderItem(27, 12, 9, 4, 25000.0, "", false),
                new OrderItem(28, 12, 6, 2, 40000.0, "", false)
        ), 4, OrderStatus.COMPLETED, LocalDateTime.of(2026, 10, 12, 19, 40));

        Order o13 = new Order(13, 5, Arrays.asList(
                new OrderItem(29, 13, 3, 2, 250000.0, "", false),
                new OrderItem(30, 13, 7, 2, 20000.0, "", false),
                new OrderItem(31, 13, 4, 2, 15000.0, "", false)
        ), 2, OrderStatus.COMPLETED, LocalDateTime.of(2026, 10, 14, 20, 30));

        Order o14 = new Order(14, 1, Arrays.asList(
                new OrderItem(32, 14, 1, 2, 65000.0, "", false),
                new OrderItem(33, 14, 6, 1, 40000.0, "", false),
                new OrderItem(34, 14, 8, 2, 5000.0, "", false)
        ), 5, OrderStatus.COMPLETED, LocalDateTime.of(2026, 10, 16, 12, 10));

        // order 15: khách đã thanh toán rồi hủy đơn -> payment REFUNDED
        Order o15 = new Order(15, 1, Arrays.asList(
                new OrderItem(35, 15, 3, 1, 250000.0, "Khách hủy đơn sau khi thanh toán, đã hoàn tiền", false)
        ), 6, OrderStatus.CANCELLED, LocalDateTime.of(2026, 10, 8, 19, 0));

        // order 16: bàn 4 đang ăn, thanh toán thẻ bị lỗi (FAILED) nên đơn chưa hoàn thành
        Order o16 = new Order(16, 4, Arrays.asList(
                new OrderItem(36, 16, 1, 1, 65000.0, "", false),
                new OrderItem(37, 16, 7, 1, 20000.0, "", false)
        ), 7, OrderStatus.SERVING, LocalDateTime.now().minusMinutes(50));

        return Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16);
    }

    public static List<Discount> getSampleDiscounts() {
        return Arrays.asList(
                new Discount(1, "VIP10", DiscountType.PERCENTAGE, 10.0,
                        LocalDateTime.of(2026, 1, 1, 0, 0), LocalDateTime.of(2027, 12, 31, 23, 59), true),
                new Discount(2, "GIAM50K", DiscountType.FIXED_AMOUNT, 50000.0,
                        LocalDateTime.of(2026, 1, 1, 0, 0), LocalDateTime.of(2027, 12, 31, 23, 59), true),
                new Discount(3, "WELCOME30K", DiscountType.FIXED_AMOUNT, 30000.0,
                        LocalDateTime.of(2026, 1, 1, 0, 0), LocalDateTime.of(2027, 12, 31, 23, 59), true),
                // mã khuyến mãi tháng 9/2026: đã hết hạn và đã tắt, nhưng vẫn còn trong lịch sử thanh toán
                new Discount(4, "SALE20", DiscountType.PERCENTAGE, 20.0,
                        LocalDateTime.of(2026, 9, 1, 0, 0), LocalDateTime.of(2026, 9, 30, 23, 59), false)
        );
    }

    /**
     * Tạo Payment từ order thật: totalAmount = tổng các món chưa hủy (số lượng x đơn giá),
     * discountAmount tính theo loại mã, finalAmount = totalAmount - discountAmount.
     * Nhờ vậy số liệu luôn khớp với order và discount.
     */
    private static Payment buildPayment(int paymentId, Order order, Discount discount,
                                        PaymentMethod method, LocalDateTime paidAt, PaymentStatus status) {
        double total = order.getItems().stream()
                .filter(i -> !i.isCancelled())
                .mapToDouble(i -> i.getQuantity() * i.getUnitPriceAtOrder())
                .sum();
        double discountAmount = 0.0;
        if (discount != null) {
            discountAmount = (discount.getType() == DiscountType.PERCENTAGE)
                    ? total * discount.getValue() / 100.0
                    : Math.min(discount.getValue(), total);
        }
        int discountId = (discount == null) ? 0 : discount.getDiscountId();
        return new Payment(paymentId, order.getOrderId(), discountId, total, discountAmount,
                total - discountAmount, method, paidAt, status);
    }

    // Mỗi order tối đa 1 payment (PaymentRepository.findByOrderId trả về 1 phiếu)
    public static List<Payment> getSamplePayments(List<Order> orders, List<Discount> discounts) {
        Function<Integer, Order> o = id -> orders.stream()
                .filter(x -> x.getOrderId() == id).findFirst().orElseThrow();
        Function<Integer, Discount> d = id -> discounts.stream()
                .filter(x -> x.getDiscountId() == id).findFirst().orElseThrow();

        return Arrays.asList(
                buildPayment(1, o.apply(3), null, PaymentMethod.CASH, o.apply(3).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(2, o.apply(5), d.apply(3), PaymentMethod.CASH, o.apply(5).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(3, o.apply(6), d.apply(1), PaymentMethod.CARD, o.apply(6).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(4, o.apply(7), null, PaymentMethod.E_WALLET, o.apply(7).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(5, o.apply(8), d.apply(1), PaymentMethod.BANK_TRANSFER, o.apply(8).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(6, o.apply(9), d.apply(4), PaymentMethod.E_WALLET, o.apply(9).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(7, o.apply(10), d.apply(1), PaymentMethod.CARD, o.apply(10).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(8, o.apply(11), d.apply(2), PaymentMethod.CASH, o.apply(11).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(9, o.apply(12), null, PaymentMethod.CARD, o.apply(12).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(10, o.apply(13), d.apply(1), PaymentMethod.E_WALLET, o.apply(13).getPaymentTime(), PaymentStatus.SUCCESS),
                buildPayment(11, o.apply(14), d.apply(1), PaymentMethod.CASH, o.apply(14).getPaymentTime(), PaymentStatus.SUCCESS),
                // order 15 đã trả tiền rồi hủy -> hoàn tiền
                buildPayment(12, o.apply(15), null, PaymentMethod.CARD, o.apply(15).getPaymentTime(), PaymentStatus.REFUNDED),
                // order 16 thanh toán thẻ lỗi
                buildPayment(13, o.apply(16), null, PaymentMethod.CARD, LocalDateTime.now().minusMinutes(5), PaymentStatus.FAILED)
        );
    }

    public static List<Table> tables = getSampleTables();
    public static List<Dish> dishes = getSampleDishes();
    public static List<Customer> customers = getSampleCustomers();
    public static List<Robot> robots = getSampleRobots();
    public static List<Order> orders = getSampleOrders();
    public static List<Category> categories = getSampleCategories();
    public static List<Discount> discounts = getSampleDiscounts();
    public static List<Payment> payments = getSamplePayments(orders, discounts);

    public static void main(String[] args) {

        System.out.println("========== BÁO CÁO 30 CÂU TRUY VẤN (JAVA LAMBDAS) ==========\n");

        // STT 1: Lọc danh sách các bàn đang trống (EMPTY)
        System.out.println("🍽️[STT 1] Bàn trống sẵn sàng đón khách (EMPTY):");
        tables.stream() //dòng chảy dữ liệu
                .filter(t -> t.getStatus() == TableStatus.EMPTY)
                .forEach(t -> System.out.println(" - Bàn ID: " + t.getId() + " (Sức chứa: " + t.getCapacity() + " người)"));

        // STT 2: Lọc các bàn đã được khách đặt trước (RESERVED) nhưng chưa đến
        System.out.println("\n🍽️[STT 2] Danh sách các bàn đang đặt trước (RESERVED):");
        tables.stream()
                .filter(t -> t.getStatus() == TableStatus.RESERVED)
                .forEach(t -> System.out.println(" - Bàn ID: " + t.getId() + " đang giữ chỗ"));

        // STT 3: Tìm các món ăn hiện tại đã HẾT HÀNG
        System.out.println("\n🍽️[STT 3] Các món ăn hiện tại đã hết hàng:");
        dishes.stream()
                .filter(d -> d.getAvailableQuantity() <= 0 || !d.isActive()) //Trả về True hoặc False
                .forEach(d -> System.out.println(" - Món: " + d.getName() + " [HẾT HÀNG]"));

        // STT 4: Xem toàn bộ danh sách các món do các thành viên trong bàn số 2 gọi
        System.out.println("\n🍽️[STT 4] Chi tiết các món được gọi tại Bàn số 2:");
        orders.stream()
                .filter(o -> o.getTableId() == 2)
                .flatMap(o -> o.getItems().stream())
                //Trải phằng các DS lồng nhau thành 1 luồng (gom các đối tượng OrderItem nằm bên trong danh sách các Order lại với nhau)
                .forEach(item -> System.out.println(" - Món ID: " + item.getDishId() + " | SL: " + item.getQuantity() + " | Ghi chú: " + item.getNote()));

        // STT 5: Tính tổng số lượng món ăn nhóm khách bàn số 2 đã gọi
        double totalItemsTable2 = orders.stream()
                .filter(o -> o.getTableId() == 2)
                .flatMap(o -> o.getItems().stream())
                .mapToDouble(OrderItem::getQuantity).sum(); //Ánh xạ từng phần tử thành kiểu số thực Double

        System.out.println("\n🍽️[STT 5] Tổng số lượng món nhóm bàn số 2 đã gọi: " + totalItemsTable2);

        // STT 6: Kiểm tra xem có Robot phục vụ (SERVING) nào đang rảnh không
        boolean hasAvailableServingRobot = robots.stream()
                .anyMatch(r -> r.getType() == RobotType.SERVING && r.getStatus() == RobotStatus.AVAILABLE);
                //anyMatch trả giá trị T/F, xem có Ít nhất 1 thỏa ĐK không
        System.out.println("\n🍽️[STT 6] Có Robot phục vụ đang rảnh không? -> " + (hasAvailableServingRobot ? "Có" : "Không (Tất cả đang bận)"));

        // STT 7: Lọc danh sách Robot đang bận (BUSY) hoặc pin yếu (< 20%)
        System.out.println("\n🍽️[STT 7] Robot đang bận hoặc pin yếu:");
        robots.stream()
                .filter(r -> r.getStatus() == RobotStatus.BUSY || r.getBatteryLevel() < 20)
                .forEach(r -> System.out.println(" - " + r.getName() + " (Trạng thái: " + r.getStatus() + ", Pin: " + r.getBatteryLevel() + "%)"));

        // STT 8: Tìm các đơn hàng bị HỦY (CANCELLED)
        System.out.println("\n🍽️[STT 8] Đơn hàng bị hủy:");
        orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.CANCELLED)
                .forEach(o -> {
                    System.out.println(" - Đơn ID: " + o.getOrderId() + " tại bàn " + o.getTableId());
                    o.getItems().forEach(item -> System.out.println("   + Ghi chú sự cố: " + item.getNote()));
                });

        // STT 9: Tìm các đơn hàng đã nấu xong đang đợi phục vụ (READY_TO_BRING)
        System.out.println("\n🍽️[STT 9] Đơn hàng nấu xong đang đợi phục vụ (READY_TO_BRING):");
        orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.READY_TO_BRING)
                .forEach(o -> System.out.println(" - Đơn ID: " + o.getOrderId() + " tại Bàn " + o.getTableId()));

        // STT 10: Thống kê số lượng đơn hàng đang nấu (PREPARING)
        long preparingCount = orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.PREPARING)
                .count();
        System.out.println("\n🍽️[STT 10] Số lượng đơn hàng đang nấu trong bếp: " + preparingCount);

        // STT 11: Lọc danh sách khách hàng VIP
        System.out.println("\n🍽️[STT 11] Danh sách khách hàng VIP:");
        customers.stream()
                .filter(c -> c.getType() == CustomerType.VIP)
                .forEach(c -> System.out.println(" - " + c.getName() + " (SĐT: " + c.getPhoneNumber() + ")"));

        // STT 12: Tìm các đơn hàng đã hoàn tất thanh toán (COMPLETED)
        System.out.println("\n🍽️[STT 12] Đơn hàng đã hoàn tất thanh toán (COMPLETED):");
        orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.COMPLETED)
                .forEach(o -> System.out.println(" - Đơn ID: " + o.getOrderId() + " bàn " + o.getTableId()));

        // STT 13: Đếm tổng số bàn đang có khách ngồi (IN_USE)
        long inUseTableCount = tables.stream()
                .filter(t -> t.getStatus() == TableStatus.IN_USE)
                .count();
        System.out.println("\n🍽️[STT 13] Tổng số bàn đang phục vụ khách (IN_USE): " + inUseTableCount);

        // STT 14: Lấy danh sách Robot nhận order (ORDER_TAKING) đang sẵn sàng
        System.out.println("\n🍽️[STT 14] Robot nhận order đang sẵn sàng:");
        robots.stream()
                .filter(r -> r.getType() == RobotType.ORDER_TAKING && r.getStatus() == RobotStatus.AVAILABLE)
                .forEach(r -> System.out.println(" - " + r.getName() + " (Pin: " + r.getBatteryLevel() + "%)"));

        // STT 15: Tính tổng doanh thu tạm tính từ các món của đơn hàng số 1
        double table2TotalBill = orders.stream()
                .filter(o -> o.getOrderId() == 1)
                .flatMap(o -> o.getItems().stream())
                .mapToDouble(item -> item.getQuantity() * item.getUnitPriceAtOrder())
                .sum();
        System.out.println("\n🍽️[STT 15] Tổng tiền tạm tính của Bàn 2: " + (long)table2TotalBill + " VNĐ");
    }
}