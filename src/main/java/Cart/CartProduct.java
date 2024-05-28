package Cart;

import Product.Product;

/**
 * Represents an item in a shopping cart, consisting of a product and its quantity.
 * This class allows for tracking and manipulating the quantity of a specific product.
 */
public class CartProduct {
    private Product product;
    private int quantity;

    /**
     * Gets the product associated with this cart item.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Gets the quantity of the product in the cart.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the cart. This method can be used to update the quantity during cart modifications.
     *
     * @param quantity the new quantity to set for the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Constructs a CartProduct with the specified product and quantity.
     *
     * @param product the product associated with this cart item
     * @param quantity the quantity of this product in the cart
     */
    public CartProduct(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the CartProduct, detailing the product title and quantity.
     * This can be useful for displaying cart contents in a user-readable format.
     *
     * @return a string representation of the CartProduct
     */
    @Override
    public String toString() {
        return "Product title: " + product.getTitle() + ", quantity: " + quantity;
    }
}
