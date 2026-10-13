package restaurant.application.service;

import restaurant.domain.entity.Discount;
import restaurant.infrastructure.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class DiscountService {
    private final DiscountRepository discountRepo;
    DiscountService(DiscountRepository discountRepo)
    {
        this.discountRepo = discountRepo;
    }

    public void addDiscount(Discount d){
        discountRepo.add(d);
    }

    public void activate(int id){
        discountRepo.findById(id).setActive(true);
    }

    public void deactivate(int id){
        discountRepo.findById(id).setActive(false);
    }
    // lấy các mã đang dùng được (còn hạn và active)
    public List<Discount> getActiveDiscounts() {
        return discountRepo.findAllActive(LocalDateTime.now());
    }

    // tìm mã theo id, trả null nếu không có
    public Discount getDiscount(int id) {
        return discountRepo.findById(id);
    }

    // mã có dùng được tại thời điểm này không
    public boolean isValid(Discount d, LocalDateTime now) {
        return discountRepo.checkValid(d, now);
    }

}
