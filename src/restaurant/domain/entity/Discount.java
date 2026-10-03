package restaurant.domain.entity;
import java.time.LocalDateTime;
import restaurant.domain.enums.DiscountType;

public class Discount
{
    DiscountType discountType;
    double price;
    String code;
    LocalDateTime validFrom;
    LocalDateTime validTo;
    boolean isActive;

    public Discount(DiscountType discountType,
                    double price,
                    String code,
                    LocalDateTime validFrom,
                    LocalDateTime validTo,
                    boolean isActive
    )
    {
        this.discountType = discountType;
        this.price = price;
        this.code = code;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.isActive = isActive;

    }

    // Chỉ có getDiscountType vì là primary key
    public DiscountType getDiscountType()
    {
        return discountType;
    }

    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }

    // Chỉ có getCode (code là primary key)
    public String getCode() {
        return code;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }
    public void setValidFrom(LocalDateTime validFrom)
    {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }
    public void setValidTo(LocalDateTime validTo)
    {
        this.validTo = validTo;
    }

    public boolean isActive()
    {
        return isActive;
    }
    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }

}
