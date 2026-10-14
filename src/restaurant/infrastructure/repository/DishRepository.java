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

}
