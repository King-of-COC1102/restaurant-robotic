package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.Category;
import restaurant.domain.entity.Dish;

import java.util.List;

public class DishRepository extends Repository<Dish, Integer>{
    public DishRepository(List<Category> list)
    {
        super(list);
    }
    public Dish findById(int id)
    {
        return findById(id, Dish::getId);
    }
}
