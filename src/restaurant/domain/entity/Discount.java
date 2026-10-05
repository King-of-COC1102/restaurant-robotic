package restaurant.domain.entity;
import java.time.LocalDateTime;
import restaurant.domain.enums.DiscountType;

public class Discount
{
    String id;
    DiscountType type;
    double price;
    LocalDateTime validFrom;
    LocalDateTime validTo;
    boolean isActive;

    public Discount(DiscountType type,
                    double price,
                    String id,
                    LocalDateTime validFrom,
                    LocalDateTime validTo,
                    boolean isActive
    )
    {
        this.type = type;
        this.price = price;
        this.id = id;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.isActive = isActive;

    }

    // Chỉ có getDiscountType vì là primary key
    public DiscountType getDiscountType()
    {
        return type;
    }

    public double getPrice()
    {
        return price;
    }

    // Chỉ có getCode (code là primary key)
    public String getId() {
        return id;
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
