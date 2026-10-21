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
    public DiscountService(DiscountRepository discountRepository, PaymentRepository paymentRepository)
    {
        this.discountRepository = discountRepository;
        this.paymentRepository = paymentRepository;
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
        System.out.printf("%-8s %-12s %-12s %-12s %-12s %s%n",
                "Order", "Mã giảm", "Loại", "Giá trị", "Tiền giảm", "Tổng tiền");
        System.out.println("-".repeat(70));

        for (Payment p : paymentRepository.findDiscount()) {
            Discount d = discountRepository.findById(p.getDiscountId());
            if (d == null) {
                continue;   // mã giảm giá đã bị xóa
            }

            String type;
            String value;

            if (d.getType() == DiscountType.PERCENTAGE) {
                type = "Phần trăm";
                value = String.format("%.0f%%", d.getValue());
            } else {
                type = "Cố định";
                value = String.format("%.0fđ", d.getValue());
            }

            System.out.printf("%-8d %-12s %-12s %-12s %-12.0f %.0f%n",
                    p.getOrderId(),
                    d.getCode(),
                    type,
                    value,
                    p.getDiscountAmount(),
                    p.getTotalAmount());
        }
    }
}
