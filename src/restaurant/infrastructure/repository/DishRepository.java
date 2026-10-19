package restaurant.infrastructure.repository;


import restaurant.domain.entity.*;


import java.util.List;

public class DishRepository extends Repository<Dish, Integer>{
    public DishRepository(List<Dish> list)
    {
        super(list);
    }
    public Dish findById(int id)
    {
        return findById(id, Dish::getId);
    }

    public List<Dish> findByCategoryId(int id)
    {
        return findBy(d -> d.getCategoryid() == id);
    }



    // câu 23: Món đang kinh doanh sắp hết hàng
    public List<Dish> lowQty()
    {
        return findBy(d -> d.isActive() && d.getAvailableQuantity() < 10);
    }

}
