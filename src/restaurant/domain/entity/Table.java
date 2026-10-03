package restaurant.domain.entity;

import restaurant.domain.enums.TableStatus;

public class Table {
    String id;
    int capacity;
    TableStatus tableStatus;

    public String getId()
    {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TableStatus getTableStatus()
    {
        return tableStatus;
    }
    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }
}
