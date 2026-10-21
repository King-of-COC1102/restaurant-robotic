package restaurant.infrastructure.repository;

import restaurant.domain.entity.*;
import restaurant.domain.enums.OrderStatus;

import java.time.YearMonth;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OrderRepository extends Repository<Order, Integer>{
    public OrderRepository(List<Order> list)
    {
        super(list);
    }
    public Order findByOrderId(int id)
    {
        return findById(id, Order::getOrderId);
    }
    public List<Order> findByCustomerId(int id)
    {
        return findBy(o -> o.getCustomerId() == id);
    }
    public List<Order> findByStatus(OrderStatus status)
    {
        return findBy(o->o.getStatus() == status);
    }

    // Câu 16: Top N món bán chạy nhất (dishId -> số phần), sắp xếp giảm dần
    public List<Map.Entry<Integer, Integer>> topNSoldByDish(int n) {
        if (n <= 0) {
            return List.of();
        }
        return totalDishSold().entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(n)
                .toList();
    }

    // Cau 20:
    public List<Order> findByTableId(int id)
    {
        return findBy(o -> o.getTableId() == id);
    }
    // cau 21: đếm số order ở mỗi table
    public Map<Integer, Long> countOrderInEachTable()
    {
        return list.stream()
                .collect(Collectors.groupingBy(Order::getTableId,
                        TreeMap::new,
                        Collectors.counting())
                );
    }

    // Câu 16 và Top N món bán chạy nhất (dishId -> số phần), sắp xếp giảm dần
    // Câu 22: Tổng số lượng bán của từng món trong các order hoàn thành
    public Map<Integer, Integer> totalDishSold() {
        return list.stream()
                .filter(o -> o.getStatus() == OrderStatus.COMPLETED)
                .flatMap(o -> o.getItems().stream())
                .filter(i -> !i.isCancelled())
                .collect(Collectors.groupingBy(OrderItem::getDishId,
                        TreeMap::new,
                        Collectors.summingInt(OrderItem::getQuantity))
                );
    }

    // 24. Bàn có ít nhất 2 order hoàn thành
    public Map<Integer, Long> atLeastTwoOrderDone()
    {
    Map<Integer, Long> map = new TreeMap<>();
        list.stream()
        .filter(o -> o.getStatus() == OrderStatus.COMPLETED)
            .collect(Collectors.groupingBy(
                    Order::getTableId,
                    TreeMap::new,
                    Collectors.counting())
                )
                .forEach((tableId, count) -> {
                    if (count >= 2) {
                        map.put(tableId, count);
                    }
                });
        return map;
    }

    // Câu 27: Các order hoàn thành trong từng tháng
    public Map<YearMonth, List<Order>> completedOrdersByMonth() {
        return list.stream()
                .filter(o -> o.getStatus() == OrderStatus.COMPLETED)
                .collect(Collectors.groupingBy(
                        o -> YearMonth.from(o.getPaymentTime()),
                        TreeMap::new,
                        Collectors.toList()));
    }

    // Câu 29: Số order theo khung giờ trong ngày
    public Map<Integer, Long> countOrdersByHour() {
        return list.stream()
                .collect(Collectors.groupingBy(
                        o -> o.getPaymentTime().getHour(),
                        TreeMap::new,
                        Collectors.counting()));
    }
}
