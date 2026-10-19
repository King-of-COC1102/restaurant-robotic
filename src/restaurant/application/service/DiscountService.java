package restaurant.application.service;

import restaurant.domain.entity.*;
import restaurant.domain.enums.DiscountType;
import restaurant.infrastructure.repository.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountService {
    private final DiscountRepository discountRepository;
    private final PaymentRepository paymentRepository;
    DiscountService(DiscountRepository discountRepo, PaymentRepository paymentRepository)
    {
        this.discountRepository = discountRepo;
        this.paymentRepository = paymentRepository;
    }

    public void addDiscount(Discount d){
        discountRepository.add(d);
    }

    public void activate(int id){
        discountRepository.findById(id).setActive(true);
    }

    public void deactivate(int id){
        discountRepository.findById(id).setActive(false);
    }
    // lấy các mã đang dùng được (còn hạn và active)
    public List<Discount> getActiveDiscounts() {
        return discountRepository.findAllActive(LocalDateTime.now());
    }

    // tìm mã theo id, trả null nếu không có
    public Discount getDiscount(int id) {
        return discountRepository.findById(id);
    }

    // mã có dùng được tại thời điểm này không
    public boolean isValid(Discount d, LocalDateTime now) {
        return discountRepository.checkValid(d, now);
    }

    // Câu 28: Phiếu có giảm giá
    public void printDiscountedPayments() {
        System.out.println("===== PHIẾU CÓ GIẢM GIÁ =====");
        System.out.printf("%-8s %-12s %-12s %-12s %-12s %-10s %s%n",
                "Order", "Mã giảm", "Loại", "Giá trị", "Tiền giảm", "Thực tế", "Tổng tiền");
        System.out.println("-".repeat(90));

        for (Payment p : paymentRepository.findDiscount()) {
            Discount d = discountRepository.findById(p.getDiscountId());

            String type;
            String value;
            String actualRate;

            if (d.getType() == DiscountType.PERCENTAGE) {
                type = "Phần trăm";
                value = d.getValue() + "%";
                actualRate = d.getValue() + "%";
            } else {
                type = "Cố định";
                value = d.getValue() + "đ";
                actualRate = p.getDiscountAmount()
                        .multiply(BigDecimal.valueOf(100))
                        .divide(p.getTotalAmount(), 1, RoundingMode.HALF_UP) + "%";
            }

            System.out.printf("%-8d %-12s %-12s %-12s %-12s %-10s %s%n",
                    p.getOrderId(),
                    d.getCode(),
                    type,
                    value,
                    p.getDiscountAmount().toPlainString(),
                    actualRate,
                    p.getTotalAmount().toPlainString());
        }
    }
}
