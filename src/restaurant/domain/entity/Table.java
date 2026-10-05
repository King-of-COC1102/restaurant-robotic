package restaurant.domain.entity;

import restaurant.domain.enums.TableStatus;

public class Table {
    int id;
    int capacity;
    TableStatus status;

    public Table(int id, int capacity, TableStatus status) {
        this.id = id;
        this.capacity = capacity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TableStatus getStatus() {
        return status;
    }
    public void setStatus(TableStatus status) {
        this.status = status;
    }
}
