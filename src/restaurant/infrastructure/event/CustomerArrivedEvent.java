package restaurant.infrastructure.event;
import restaurant.domain.entity.Customer;
import restaurant.domain.entity.Table;
import restaurant.domain.enums.TableStatus;
import restaurant.infrastructure.repository.TableRepository;

import java.util.List;

public class CustomerArrivedEvent implements Event{
    //Check bàn
    private Customer customer;
    private TableRepository tableRepository;
    public CustomerArrivedEvent(Customer customer, TableRepository tableRepository)
    {
        this.customer=customer;
        this.tableRepository=tableRepository;
    }
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void  fireEvent() {
        System.out.println("Đang xử lí tiếp đón khách "+customer.getName());
        System.out.println(".......");
        if (tableRepository!=null) {
            List<Table> emptyTables=tableRepository.findByStatus(TableStatus.EMPTY);
            if(emptyTables.isEmpty()) {
                System.out.println("->Rất tiếc! Nhà hàng bán đắt khách quá nên hết bàn òii. Hẹn gặp lại");
            }
            else {
                Table emptyTable=emptyTables.get(0); //Lấy vị trí đầu tiên trong DS bàn trống
                emptyTable.setStatus(TableStatus.IN_USE);
                System.out.println("->Đã có bàn trống cho khách "+customer.getName());
                System.out.println("  Trạng thái bàn: Đang sử dụng");
            }
        }
        else {
            System.out.println(" Lỗi: Sự kiện chưa được cung cấp đủ dữ liệu!");
        }
    }
}
