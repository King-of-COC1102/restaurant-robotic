package restaurant.infrastructure.repository;

import restaurant.app.SampleData;
import restaurant.domain.entity.Category;
import restaurant.domain.entity.Payment;

import java.util.List;
import java.util.function.Function;

public class PaymentRepository extends Repository<Payment, Integer>{
    public PaymentRepository(List<Category> list)
    {
        super(list);
    }

    public Payment findById(int id) {
        return super.findById(id, Payment::getPaymentId);
    }

}
