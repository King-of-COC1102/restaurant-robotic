package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.*;

import java.util.List;

public class TableRepository extends Repository<Table, Integer>{
    public TableRepository(List<Category> list) {
        super(list);
    }
    public Table findById(int id) {
        return findById(id, Table::getId);
    }
}
