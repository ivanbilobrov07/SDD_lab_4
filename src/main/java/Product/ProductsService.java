package Product;

import Filter.FilterService;
import Sort.SortStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing products, including sorting and filtering functionality.
 */
public class ProductsService {
    private List<Product> products;
    private SortStrategy sortStrategy;
    private FilterService filterService;

    /**
     * Constructs a ProductsService with the specified initial sort strategy and filter service.
     *
     * @param initialSortStrategy the initial sort strategy to be used
     * @param filterService the filter service to be used
     */
    public ProductsService(SortStrategy initialSortStrategy, FilterService filterService){
        this.products = new ArrayList<Product>();
        this.sortStrategy = initialSortStrategy;
        this.filterService = filterService;
    }

    /**
     * Retrieves the current sort strategy.
     *
     * @return the current sort strategy
     */
    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }

    /**
     * Sets the sort strategy to be used.
     *
     * @param sortStrategy the sort strategy to be set
     */
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    /**
     * Adds a product to the list of products.
     *
     * @param product the product to be added
     */
    public void addProduct(Product product){
        this.products.add(product);
    }

    /**
     * Removes a product from the list of products.
     *
     * @param product the product to be removed
     */
    public void removeProduct(Product product) {
       this.products.remove(product);
    }

    /**
     * Retrieves a list of products filtered and sorted based on the provided name.
     *
     * @param name the name to filter the products by
     * @return a list of products filtered and sorted based on the provided name
     */
    public List<Product> getProducts(String name){
        List<Product> filteredProducts = products;

        if(filterService != null){
            filteredProducts = this.filterService.filterProducts(this.products, name);
        }

        if(sortStrategy != null){
            return this.sortStrategy.sortProducts(filteredProducts);
        }

        return  filteredProducts;
    }


    /**
     * Prints the applied filters.
     */
    public void printAppliedFilters(){
        System.out.println("Applied filters: ");
        System.out.println(this.filterService.toString());
    }
}
