package restaurant.application.service;

import restaurant.domain.entity.*;
import restaurant.infrastructure.repository.*;

import java.util.List;

public class MenuService {
    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;
    public MenuService(DishRepository dishRepository, CategoryRepository categoryRepository)
    {
        this.dishRepository = dishRepository;
        this.categoryRepository = categoryRepository;
    }

    // Câu 17: Số món theo danh mục (== IN ra MENU)
    public void printDishCountByCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            System.out.println("Chưa có danh mục nào.");
            return;
        }

        System.out.println("===== SỐ MÓN THEO DANH MỤC =====");
        System.out.printf("%-5s %-25s %s%n", "ID", "Danh mục", "Số món");
        System.out.println("-".repeat(40));

        categories.forEach(c -> {
            int count = dishRepository.findByCategoryId(c.getId()).size();
            System.out.printf("%-5d %-25s %d%n", c.getId(), c.getName(), count);
        });
    }

    // Câu 23: Món đang kinh doanh sắp hết hàng
    public void printLowQuantityDishes() {
        List<Dish> dishes = dishRepository.lowQty();
        if (dishes.isEmpty()) {
            System.out.println("Không có món nào sắp hết hàng.");
            return;
        }
        System.out.println("===== MÓN SẮP HẾT HÀNG =====");
        System.out.printf("%-5s %-25s %s%n", "ID", "Tên món", "Còn lại");
        System.out.println("-".repeat(45));

        dishes.forEach(d ->
                System.out.printf("%-5d %-25s %d%n", d.getId(), d.getName(), d.getAvailableQuantity()));
    }
}
