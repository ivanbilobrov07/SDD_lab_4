package Product;


/**
 * Represents a product.
 */
public class Product {
    private String id;
    private String title;
    private float price;
    private String description;
    private String brand;
    private String category;
    private int quantity;
    private String image;

    /**
     * Constructs a product with the specified attributes.
     *
     * @param id the ID of the product
     * @param title the title of the product
     * @param price the price of the product
     * @param description the description of the product
     * @param brand the brand of the product
     * @param category the category of the product
     * @param quantity the quantity of the product
     * @param image the image URL of the product
     */
    public Product(String id, String title, float price, String description, String brand, String category, int quantity, String image){
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.quantity = quantity;
        this.image = image;
    }

    /**
     * Retrieves the title of the product.
     *
     * @return the title of the product
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product
     */
    public float getPrice() {
        return price;
    }

    /**
     * Retrieves the brand of the product.
     *
     * @return the brand of the product
     */
    public String getBrand() { return  brand; }

    /**
     * Retrieves the category of the product.
     *
     * @return the category of the product
     */
    public String getCategory(){ return category; }

    /**
     * Retrieves the quantity of the product.
     *
     * @return the quantity of the product
     */
    public int getQauntity(){ return quantity; }

    /**
     * Changes the quantity of the product.
     *
     * @param quantity the new quantity of the product
     */
    public void changeQuantity(int quantity){
        this.quantity = quantity;
    }


    /**
     * Retrieves detailed information about the product.
     *
     * @return detailed information about the product
     */
    public String getInfo() {
        return "Product {" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    /**
     * Retrieves a string representation of the product.
     *
     * @return a string representation of the product
     */
    @Override
    public String toString() {
        return "Product {" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
