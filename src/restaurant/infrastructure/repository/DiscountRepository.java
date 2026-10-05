package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.Category;
import restaurant.domain.entity.Discount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepository extends Repository<Discount, Integer>
{
    public DiscountRepository(List<Category> list)
    {
        super(list);
    }

    // tìm discount thông qua id của nó
    public Discount findById(int id)
    {
        return findById(id, Discount::getDiscountId);
    }

    // trả ra list các discount còn hoạt động (còn hạn và đang được setActive = true)
    public List<Discount> findAllActive(LocalDateTime now) {
        return findBy(d -> d.isActive()&&
                !now.isBefore(d.getValidFrom())
                && !now.isAfter(d.getValidTo())
        );
    }
}
