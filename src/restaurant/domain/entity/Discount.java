package restaurant.domain.entity;
import java.time.LocalDateTime;
import restaurant.domain.enums.DiscountType;

public class Discount
{
    int discountId;
    String code;
    DiscountType type;
    double value;
    LocalDateTime validFrom;
    LocalDateTime validTo;
    boolean isActive;

    public Discount(int discountId,
                    String code,
                    DiscountType type,
                    double value,
                    LocalDateTime validFrom,
                    LocalDateTime validTo,
                    boolean isActive) {
        this.discountId = discountId;
        this.code = code;
        this.type = type;
        this.value = value;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.isActive = isActive;
    }

    public int getDiscountId() {
        return discountId;
    }

    public String getCode() {
        return code;
    }

    public DiscountType getType() {
        return type;
    }
    public void setType(DiscountType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }
    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }
    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
