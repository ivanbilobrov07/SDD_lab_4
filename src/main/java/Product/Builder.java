package Product;

/**
 * Interface for building product objects using the builder pattern.
 */
public interface Builder {
    /**
     * Sets the title of the product.
     *
     * @param title the title of the product
     * @return the builder instance
     */
    Builder setTitle(String title);

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     * @return the builder instance
     */
    Builder setPrice(float price);

    /**
     * Sets the description of the product.
     *
     * @param description the description of the product
     * @return the builder instance
     */
    Builder setDescription(String description);

    /**
     * Sets the brand of the product.
     *
     * @param brand the brand of the product
     * @return the builder instance
     */
    Builder setBrand(String brand);

    /**
     * Sets the category of the product.
     *
     * @param category the category of the product
     * @return the builder instance
     */
    Builder setCategory(String category);

    /**
     * Sets the quantity of the product.
     *
     * @param quantity the quantity of the product
     * @return the builder instance
     */
    Builder setQuantity(int quantity);

    /**
     * Sets the image of the product.
     *
     * @param image the image URL of the product
     * @return the builder instance
     */
    Builder setImage(String image);

    /**
     * Builds and returns the product object.
     *
     * @return the product object built using the provided parameters
     */
    Product build();
}
