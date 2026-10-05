package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.Discount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepository extends Repository<Discount, String>
{
    DiscountRepository()
    {
        super(SampleData.DISCOUNTS);
    }

    // tìm discount thông qua id của nó
    public Discount findById(String id)
    {
        return findById(id, d -> d.getId());
    }

    // trả ra list các discount còn hoạt động (còn hạn và đang được setActive = true)
    public List<Discount> findAllActive(LocalDateTime now) {
        return findBy(d -> d.isActive()&&
                !now.isBefore(d.getValidFrom())
                && !now.isAfter(d.getValidTo())
        );
    }
}
