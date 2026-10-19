package restaurant.infrastructure.repository;

import restaurant.domain.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepository extends Repository<Discount, Integer>
{
    public DiscountRepository(List<Discount> list)
    {
        super(list);
    }

    // tìm discount thông qua id của nó
    public Discount findById(int id)
    {
        return findById(id, Discount::getDiscountId);
    }

    // mã có dùng được tại thời điểm này không
    public boolean checkValid(Discount d, LocalDateTime now) {
        return d != null && d.isActive()
                && !now.isBefore(d.getValidFrom())
                && !now.isAfter(d.getValidTo());
    }

    // trả ra list các discount còn hoạt động (còn hạn và đang được setActive = true)
    public List<Discount> findAllActive(LocalDateTime now) {
        return findBy(d -> d.isActive()&&
                !now.isBefore(d.getValidFrom())
                && !now.isAfter(d.getValidTo())
        );
    }
}
