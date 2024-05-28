package Sort;

import Product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of SortStrategy for sorting products by title.
 */
public class SortByTitle implements SortStrategy{
    /**
     * Sorts the list of products by title.
     *
     * @param products the list of products to be sorted
     * @return the sorted list of products
     */
    @Override
    public List<Product> sortProducts(List<Product> products) {
        List<Product> sortedProducts = new ArrayList<Product>(products);

        sortedProducts.sort(Comparator.comparing(Product::getTitle));
        return sortedProducts;
    }

    /**
     * Returns a string representation of this sorting strategy.
     *
     * @return a string representation of this sorting strategy
     */
    @Override
    public String toString(){
        return "Sorting by title";
    }
}

