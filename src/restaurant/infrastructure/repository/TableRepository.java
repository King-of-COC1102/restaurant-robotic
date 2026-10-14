package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.*;
import restaurant.domain.enums.TableStatus;

import java.util.ArrayList;
import java.util.List;

public class TableRepository extends Repository<Table, String>{
    public TableRepository(List<Table> list) {
        super(list);
    }
    public Table findById(String id) {
        return findById(id, Table::getId);
    }
    //Tìm trạng thái của bàn (xem còn trống không)
    public List<Table> findByStatus(TableStatus status) {
        List<Table> result= new ArrayList<>();
        for (Table table:list) { // Duyệt qua danh sách bàn (list được kế thừa từ lớp Repository)
            if (table.getStatus()==status) { //Tìm các bàn trống cho khác
                result.add(table); //DS bàn tống
            }
        }
        return result;
    }

}
