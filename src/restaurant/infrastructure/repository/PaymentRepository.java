package restaurant.infrastructure.repository;

import restaurant.domain.entity.*;
import restaurant.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PaymentRepository extends Repository<Payment, Integer>{
    public PaymentRepository(List<Payment> list)
    {
        super(list);
    }

    public Payment findByPaymnetId(int id) {
        return super.findById(id, Payment::getPaymentId);
    }

    public Payment findByOrderId(int id) {
        return super.findById(id, Payment::getOrderId);
    }

    public List<Payment> findDiscount() {
        return findBy(p -> p.getStatus() == PaymentStatus.SUCCESS);
    }
    // 25 top n phiếu có phương thức thanh toán cao nhất
    public List<Payment> TopNPayments(int n) {
        return findBy(p -> p.getStatus() == PaymentStatus.SUCCESS).stream()
                .sorted((a, b) -> b.getFinalAmount().compareTo(a.getFinalAmount()))
                .limit(n).toList();
    }

    // Câu 30: Trung bình final amount của các phiếu thanh toán thành công
    public BigDecimal averagePaidAmount() {
        List<Payment> paid = findBy(p -> p.getStatus() == PaymentStatus.SUCCESS);
        if (paid.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = BigDecimal.ZERO;

        for (Payment p : paid) {
            sum = sum.add(p.getFinalAmount());
        }

        return sum.divide(
                BigDecimal.valueOf(paid.size()),
                2,
                RoundingMode.HALF_UP
        );
    }

    // Câu 30: Phiếu thanh toán có giá trị trên mức trung bình
    public List<Payment> paymentsAboveAverage() {
        BigDecimal avg = averagePaidAmount();
        return findBy(p -> p.getStatus() == PaymentStatus.SUCCESS)
                .stream()
                .filter(p -> p.getFinalAmount().compareTo(avg) > 0)
                .toList();
    }
}
