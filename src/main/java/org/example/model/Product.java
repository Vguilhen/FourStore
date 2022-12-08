package org.example.model;


import org.example.enums.*;

public class Product {

    private Long id;
    private String sku;
    private String description;
    private int quantity;
    private Double price;
    private CategoryEnum category;
    private ColorEnum color;
    private DepartmentEnum department;
    private SizeEnum size;
    private TypeEnum type;

    public Product() {
    }

    public Product(Long id, String sku, String description, int quantity, Double price) {
        super();
        this.id = id;
        this.sku = sku;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        parseSku();
    }

    public Product(int quantity) {
    }

    private void parseSku() {
        this.category = CategoryEnum.getCategoryEnum(sku.substring(0, 2));
        this.color = ColorEnum.getColorEnum(sku.substring(2, 4));
        this.department = DepartmentEnum.getDepartmentEnum(sku.substring(4, 7));
        this.size = SizeEnum.getSizeEnum(Integer.parseInt(sku.substring(7, 9)));
        this.type = TypeEnum.getTypeEnum(sku.substring(9, 12));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  "\n" +
                "|----------------------------------------------------|\n" +
                "| SKU: " + sku + ",                                   \n" +
                "| Description: " + description + ",                   \n" +
                "| Category: " + category + ",                         \n" +
                "| Color: " + color + ",                               \n" +
                "| Department: " + department + ",                     \n" +
                "| Size: " + size + ",                                 \n" +
                "| Type: " + type + ",                                 \n" +
                "| Quantity: " + quantity + ",                         \n" +
                "| Price: R$" + price + ",                             \n" +
                "| ---------------------------------------------------|\n";
    }
}
