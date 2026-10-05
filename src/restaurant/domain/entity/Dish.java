package restaurant.domain.entity;

public class Dish
{
    int dishId;
    int categoryid;
    String name;
    double price;
    int AvailableQuantity;
    String description;
    boolean isActive;

    Dish(int dishId,
         int categoryid,
         String name,
         double price,
         int AvailableQuantity,
         String description,
         boolean isActive
    )
    {
        this.dishId = dishId;
        this.categoryid = categoryid;
        this.name = name;
        this.price = price;
        this.AvailableQuantity = AvailableQuantity;
        this.description = description;
        this.isActive = isActive;
    }
    // GET METHOD

    public int getId() {
        return dishId;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return AvailableQuantity;
    }
    public void setAvailableQuantity(int availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
