package restaurant.app;
import restaurant.application.service.*;
import restaurant.domain.entity.*;
import restaurant.domain.enums.*;
import restaurant.infrastructure.event.*;
import restaurant.infrastructure.repository.*;

import java.util.ArrayList;
import java.util.List;

import static restaurant.app.SampleData.*;

public class Main {

    public static void main(String[] args) {

//        RunEvent event = new RunEvent();
//        SampleData.main(args);

        TableRepository tableRepo = new TableRepository(tables);
        DishRepository dishRepo = new DishRepository(dishes);
        CategoryRepository categoryRepo = new CategoryRepository(categories);
        CustomerRepository customerRepo = new CustomerRepository(customers);
        RobotRepository robotRepo = new RobotRepository(robots);
        OrderRepository orderRepo = new OrderRepository(orders);
        PaymentRepository paymentRepo = new PaymentRepository(payments);
        DiscountRepository discountRepo = new DiscountRepository(discounts);

        RestaurantAnalyticsService analytics = new RestaurantAnalyticsService(
                orderRepo, dishRepo, tableRepo, paymentRepo, customerRepo, robotRepo);

        MenuService menuService = new MenuService(dishRepo, categoryRepo);

        DiscountService discountService = new DiscountService(discountRepo, paymentRepo);

        TableService tableService = new TableService(tableRepo, orderRepo);

        System.out.println("\n========== BÁO CÁO CÂU 16 - 30 ==========");

        System.out.println("\n[STT 16]");
        analytics.printTopNBestSellers(3);

        System.out.println("\n️[STT 17]");
        menuService.printDishCountByCategory();

        System.out.println("\n[STT 18]");
        analytics.printRobotStatsByType();

        System.out.println("\n[STT 19]");
        analytics.printReturningCustomers();

        System.out.println("\n[STT 20]");
        analytics.printRevenueByTable();

        System.out.println("\n[STT 21]");
        tableService.printcountOrderInEachTable();

        System.out.println("\n[STT 22]");
        analytics.printTotalDishSold();

        System.out.println("\n[STT 23]");
        menuService.printLowQuantityDishes();

        System.out.println("\n[STT 24]");
        analytics.printTablesWithAtLeastTwoCompletedOrders();

        System.out.println("\n[STT 25]");
        analytics.printTopNPayments(3);

        System.out.println("\n[STT 26]");
        analytics.printCustomerAndRevenueByType();

        System.out.println("\n[STT 27]");
        analytics.printCompletedOrdersByMonth();

        System.out.println("[STT 28]");
        discountService.printDiscountedPayments();

        System.out.println("\n[STT 29]");
        analytics.printOrdersByHour();

        System.out.println("\n[STT 30]");
        analytics.printPaymentsAboveAverage();
    }
}