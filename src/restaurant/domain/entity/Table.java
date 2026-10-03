package restaurant.domain.entity;

import restaurant.domain.enums.TableStatus;

public class Table {
    String id;
    TableStatus tableStatus;

    public String getId()
    {
        return id;
    }
    public TableStatus getTableStatus()
    {
        return tableStatus;
    }
}
