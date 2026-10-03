package restaurant.domain.entity;

public class Dish
{
    String id;
    String name;
    double price;
    int AvailableQuantity;
    boolean isActive;
    String categoryId ;

    Dish(String id,
         String name,
         double price,
         int AvailableQuantity,
         boolean isActive,
         String categoryId
    )
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.AvailableQuantity = AvailableQuantity;
        this.isActive = isActive;
        this.categoryId = categoryId;
    }
    // GET METHOD

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableQuantity() {
        return AvailableQuantity;
    }
    public void setAvailableQuantity(int availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
