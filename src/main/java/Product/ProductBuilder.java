package Product;

import java.util.UUID;


/**
 * Implementation of the Builder interface for building Product objects.
 */
public class ProductBuilder implements Builder {
    private String id;
    private String title;
    private float price;
    private String description;
    private String brand;
    private String category;
    private int quantity;
    private String image;

    /**
     * Sets the title of the product being built.
     *
     * @param title the title of the product
     * @return the builder instance
     * @throws IllegalArgumentException if the title is empty
     */
    @Override
    public ProductBuilder setTitle(String title) {
        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        this.title = title;
        return this;
    }

    /**
     * Sets the price of the product being built.
     *
     * @param price the price of the product
     * @return the builder instance
     * @throws IllegalArgumentException if the price is negative
     */
    @Override
    public ProductBuilder setPrice(float price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.price = price;
        return this;
    }


    /**
     * Sets the description of the product being built.
     *
     * @param description the description of the product
     * @return the builder instance
     */
    @Override
    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }


    /**
     * Sets the brand of the product being built.
     *
     * @param brand the brand of the product
     * @return the builder instance
     * @throws IllegalArgumentException if the brand is empty
     */
    @Override
    public ProductBuilder setBrand(String brand) {
        if (brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }

        this.brand = brand;
        return this;
    }

    /**
     * Sets the category of the product being built.
     *
     * @param category the category of the product
     * @return the builder instance
     * @throws IllegalArgumentException if the category is empty
     */
    @Override
    public ProductBuilder setCategory(String category) {
        if (category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }

        this.category = category;
        return this;
    }

    /**
     * Sets the quantity of the product being built.
     *
     * @param quantity the quantity of the product
     * @return the builder instance
     * @throws IllegalArgumentException if the quantity is negative
     */
    @Override
    public ProductBuilder setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        this.quantity = quantity;
        return this;
    }

    /**
     * Sets the image of the product being built.
     *
     * @param image the image URL of the product
     * @return the builder instance
     */
    @Override
    public ProductBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * Builds and returns the product object.
     *
     * @return the product object built using the provided parameters
     */
    @Override
    public Product build() {
        this.id = UUID.randomUUID().toString();

        return new Product(id, title, price, description, brand, category, quantity, image);
    }
}
