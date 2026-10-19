package restaurant.application.service;

import restaurant.domain.entity.Table;
import restaurant.infrastructure.repository.*;

import java.util.Map;

public class TableService {
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;
    TableService(TableRepository tableRepository, OrderRepository orderRepository)
    {
        this.tableRepository = tableRepository;
        this.orderRepository = orderRepository;
    }
    // cau 21: đếm số order ở mỗi table
    public void printcountOrderInEachTable()
    {
        Map<Integer, Long> result = orderRepository.countOrderInEachTable();
        System.out.println("===== TỔNG SỐ LẦN ĐẶT MÓN Ở MỖI BÀN =====");
        result.forEach((tableId, count) ->
                System.out.printf("Table ID[%d]: %d order%n", tableId, count));

    }
}
