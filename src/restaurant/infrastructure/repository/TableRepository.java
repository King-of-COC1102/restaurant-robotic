package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.*;
import restaurant.domain.enums.TableStatus;

import java.util.ArrayList;
import java.util.List;

public class TableRepository extends Repository<Table, Integer>{
    public TableRepository(List<Table> list) {
        super(list);
    }
    public Table findById(int id) {
        return findById(id, Table::getId);
    }
    //Tìm trạng thái của bàn (xem còn trống không)
    public List<Table> findByStatus(TableStatus status) {
        // tìm bàn có trạng thái trống
        return findBy(t -> t.getStatus() == status);
    }

}
