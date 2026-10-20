package restaurant.app;

import restaurant.domain.entity.*;
import restaurant.domain.enums.*;

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
                new Dish(5, 101, "Bò Wagyu Thượng Hạng", 600000.0, 0, "Thịt bò cao cấp nướng đá (Tạm hết)", false)
        );
    }

    public static List<Customer> getSampleCustomers() {
        return Arrays.asList(
                new Customer(1, "Nguyen Van An", "0901234567", CustomerType.REGULAR, LocalDateTime.now().minusMinutes(40)),
                new Customer(2, "Tran Thi Bich", "0912345678", CustomerType.VIP, LocalDateTime.now().minusMinutes(10)),
                new Customer(3, "Le Hoang Nam (Đặt trước)", "0923456789", CustomerType.VIP, LocalDateTime.now().plusMinutes(20)),
                new Customer(4, "Pham Minh Duc", "0934567890", CustomerType.REGULAR, LocalDateTime.now().minusMinutes(5))
        );
    }

    public static List<Robot> getSampleRobots() {
        return Arrays.asList(
                new Robot(1, "CookBot-01", RobotType.KITCHEN, RobotStatus.AVAILABLE, 100, 1),
                new Robot(2, "ServeBot-01", RobotType.SERVING, RobotStatus.BUSY, 80, 1),
                new Robot(3, "OrderBot-01", RobotType.ORDER_TAKING, RobotStatus.AVAILABLE, 90, 1),
                new Robot(4, "CookBot-02", RobotType.KITCHEN, RobotStatus.BUSY, 15, 1)
        );
    }

    public static List<Order> getSampleOrders() {
        List<OrderItem> itemsBan2 = new ArrayList<>();
        itemsBan2.add(new OrderItem(1, 1, 1, 1, 65000.0, "Người 1: Phở không hành", false));
        itemsBan2.add(new OrderItem(2, 1, 2, 1, 45000.0, "Người 2: Cơm sườn", false));
        itemsBan2.add(new OrderItem(3, 1, 4, 3, 15000.0, "Người 3: 3 lon coca", false));

        List<OrderItem> itemsBan5 = Arrays.asList(
                new OrderItem(4, 2, 3, 1, 250000.0, "Ít cay", false)
        );

        List<OrderItem> itemsBan4 = Arrays.asList(
                new OrderItem(5, 3, 1, 1, 65000.0, "Ăn tại chỗ", false)
        );

        List<OrderItem> itemsBiHuy = Arrays.asList(
                new OrderItem(6, 4, 5, 1, 600000.0, "Khách chọn món Bò Wagyu nhưng kho đã hết", true)
        );

        Order o1 = new Order(1, 2, itemsBan2, 1, OrderStatus.PREPARING, null);
        Order o2 = new Order(2, 5, itemsBan5, 2, OrderStatus.READY_TO_BRING, null);
        Order o3 = new Order(3, 4, itemsBan4, 4, OrderStatus.COMPLETED, LocalDateTime.now());
        Order o4 = new Order(4, 1, itemsBiHuy, 1, OrderStatus.CANCELLED, null);

        return Arrays.asList(o1, o2, o3, o4);
    }

    public static void main(String[] args) {
        List<Table> tables = getSampleTables();
        List<Dish> dishes = getSampleDishes();
        List<Customer> customers = getSampleCustomers();
        List<Robot> robots = getSampleRobots();
        List<Order> orders = getSampleOrders();

        System.out.println("========== BÁO CÁO 15 CÂU TRUY VẤN (JAVA LAMBDAS) ==========\n");

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