package restaurant.infrastructure.repository;

import restaurant.domain.entity.*;
import restaurant.domain.enums.CustomerType;

import java.util.List;

public class CustomerRepository extends Repository<Customer, Integer>{
    public CustomerRepository(List<Customer> list)
    {
        super(list);
    }

    public Customer findById(int id) {
        return findById(id, Customer::getCustomerId);
    }

    public List<Customer> findByType(CustomerType type) {
        return findBy(c -> c.getType() == type);
    }
}
