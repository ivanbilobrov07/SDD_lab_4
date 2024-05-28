package Sort;

import Product.Product;

import java.util.List;


/**
 * Interface for defining sorting strategies for products.
 */
public interface SortStrategy {

    /**
     * Sorts the list of products using the implemented sorting strategy.
     *
     * @param products the list of products to be sorted
     * @return the sorted list of products
     */
    List<Product> sortProducts(List<Product> products);

    /**
     * Returns a string representation of this sorting strategy.
     *
     * @return a string representation of this sorting strategy
     */
    String toString();
}
