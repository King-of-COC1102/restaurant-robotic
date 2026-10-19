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

    // Câu 25: Top N phiếu thanh toán có số tiền cao nhất
    public List<Payment> TopNPayments(int n) {
        if (n <= 0) {
            return List.of();
        }
        return findBy(p -> p.getStatus() == PaymentStatus.SUCCESS).stream()
                .sorted((a, b) -> Double.compare(b.getFinalAmount(), a.getFinalAmount()))
                .limit(n)
                .toList();
    }

    // Câu 30: Trung bình final amount của các phiếu thanh toán thành công
    public double averagePaidAmount() {
        return findBy(p -> p.getStatus() == PaymentStatus.SUCCESS).stream()
                .mapToDouble(Payment::getFinalAmount)
                .average()
                .orElse(0);
    }

    // Câu 30: Phiếu thanh toán có giá trị trên mức trung bình
    public List<Payment> paymentsAboveAverage() {
        double avg = averagePaidAmount();
        return findBy(p -> p.getStatus() == PaymentStatus.SUCCESS).stream()
                .filter(p -> p.getFinalAmount() > avg)
                .toList();
    }
}
