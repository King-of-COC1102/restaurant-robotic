package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.Category;

import java.util.List;

public class CategoryRepository extends Repository<Category, Integer>{
    public CategoryRepository(List<Category> list)
    {
        super(list);
    }
    public Category findById(int id)
    {
        return findById(id, Category::getId);
    }
}
