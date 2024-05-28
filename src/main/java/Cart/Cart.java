package Cart;

import Exceptions.EmptyException;
import Product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart containing products along with their respective quantities.
 * It provides methods to manage the cart, such as adding or removing products, and adjusting quantities.
 */
public class Cart {
    private List<CartProduct> products;
    private float totalPrice;

    /**
     * Gets the total price of all products in the cart.
     *
     * @return the total price of the cart
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * Checks if the cart is empty.
     *
     * @return true if the cart has no products, false otherwise
     */
    public boolean isEmpty(){
        return products.isEmpty();
    }

    /**
     * Returns a list of products in the cart.
     *
     * @return a list of CartProduct objects
     */
    public List<CartProduct> getProducts() {
        return products;
    }


    /**
     * Constructs an empty shopping cart with a total price initialized to zero.
     */
    public Cart(){
        products = new ArrayList<>();
        totalPrice = 0;
    }

    /**
     * Recalculates the total price based on the products and their quantities in the cart.
     */
    private void recalculateTotalPrice(){
        float newTotalPrice = 0;

        for(CartProduct cartProduct : products){
            newTotalPrice += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
        }

        this.totalPrice = newTotalPrice;
    }

    /**
     * Adds a product to the cart or increments the quantity of an existing product.
     *
     * @param product the product to add
     * @param quantity the quantity of the product to add
     * @throws Exception if the quantity exceeds the available stock
     */
    public void addProduct(Product product, int quantity) throws Exception {
        if(quantity > product.getQauntity()){
            throw new Exception("You can't add more than " + product.getQauntity() + " items");
        }

        for (CartProduct cartProduct : products) {
            if (cartProduct.getProduct().equals(product)) {
                if(cartProduct.getQuantity() + quantity > product.getQauntity()){
                    throw new Exception("You can't add more than " + product.getQauntity() + " items");
                }

                cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
                recalculateTotalPrice();
                return;
            }
        }

        this.products.add(new CartProduct(product, quantity));
        recalculateTotalPrice();
    }

    /**
     * Removes a product from the cart.
     *
     * @param product the product to remove
     */
    public void removeProduct(CartProduct product){
        this.products.removeIf(cartProduct -> cartProduct.equals(product));
        recalculateTotalPrice();
    }

    /**
     * Changes the quantity of a specific product in the cart.
     *
     * @param product the product for which the quantity is being changed
     * @param newQuantity the new quantity to set for the product
     * @throws Exception if the new quantity is invalid or exceeds available stock
     */
    public void changeQuantity(CartProduct product, int newQuantity) throws Exception {
        for (CartProduct cartProduct : products) {
            if (cartProduct.equals(product)) {
                if (newQuantity > 0 && cartProduct.getProduct().getQauntity() >= newQuantity) {
                    cartProduct.setQuantity(newQuantity);
                } else if (newQuantity == 0) {
                    this.removeProduct(cartProduct);
                } else {
                    throw new Exception("Incorrect quantity value");
                }
            }
        }

        recalculateTotalPrice();
    }

    /**
     * Logs all products in the cart along with the total price to the console.
     * Throws an exception if the cart is empty.
     *
     * @throws EmptyException if the cart is empty
     */
    public void logCart() throws EmptyException {
        if (products.isEmpty()) {
            throw new EmptyException("The cart is empty");
        } else {
            for (CartProduct cartProduct : products) {
                System.out.println(cartProduct);
            }
            System.out.println("Total price: " + totalPrice);
        }
    }

    /**
     * Decrements the quantity of each product in the store inventory based on the quantities in the cart.
     */
    public void removeQuantityInProducts(){
        for (CartProduct cartProduct : products) {
            Product product = cartProduct.getProduct();
            product.changeQuantity(product.getQauntity() - cartProduct.getQuantity());
        }
    }

    /**
     * Returns a string representation of the cart, listing products and their details along with the total price.
     *
     * @return a string detailing the contents of the cart
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Cart { products = [ ");

        for (int i = 1; i <= products.size(); i++){
            output.append(products.get(i - 1).toString());

            if(i != products.size()){
                output.append(", ");
            } else {
                output.append(" ], totalPrice = ").append(totalPrice);
            }
        }

        return output.toString();
    }
}
