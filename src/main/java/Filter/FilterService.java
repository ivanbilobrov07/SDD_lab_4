package Filter;

import Product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class that manages filters for products.
 */
public class FilterService {
    private List<String> brandFilters = new ArrayList<>();
    private List<String> categoryFilters = new ArrayList<>();
    private Float minPrice = null;
    private Float maxPrice = null;

    /**
     * Constructs an empty FilterService.
     */
    public FilterService(){}

    /**
     * Adds a brand to the list of brand filters.
     *
     * @param brand the brand to add to the filters
     */
    public void addBrandToFilter(String brand){
        if(!this.brandFilters.contains(brand)){
            this.brandFilters.add(brand);
        }
    }

    /**
     * Adds a category to the list of category filters.
     *
     * @param category the category to add to the filters
     */
    public void addCategoryFilter(String category){
        if(!this.categoryFilters.contains(category)){
            this.categoryFilters.add(category);
        }
    }

    /**
     * Sets the minimum or maximum price filter.
     *
     * @param filterType the type of price filter (MIN_PRICE or MAX_PRICE)
     * @param value the value to set for the price filter
     */
    public void changePriceFilters(FilterType filterType, Float value){
        if(filterType == FilterType.MIN_PRICE){
            minPrice = value;
        }
        else if (filterType == FilterType.MAX_PRICE){
            maxPrice = value;
        }
    }

    /**
     * Removes a brand or category filter from the respective filter list.
     *
     * @param filterType the type of filter to remove (BRAND or CATEGORY)
     * @param filter the filter value to remove
     */
    public void removeArrayFilter(FilterType filterType, String filter){
        if(filterType == FilterType.BRAND){
            this.brandFilters.remove(filter);
        }
        else if (filterType == FilterType.CATEGORY){
            this.categoryFilters.remove(filter);
        }
    }

    /**
     * Clears all brand or category filters.
     *
     * @param filterType the type of filters to clear (BRAND or CATEGORY)
     */
    public void clearAllArrayFilters(FilterType filterType){
        if(filterType == FilterType.BRAND){
            this.brandFilters.clear();
        }
        else if (filterType == FilterType.CATEGORY){
            this.categoryFilters.clear();
        }
    }

    /**
     * Clears all filters, including brand, category, and price filters.
     */
    public void clearAllFilters(){
        this.categoryFilters.clear();
        this.brandFilters.clear();
        this.maxPrice = null;
        this.minPrice = null;
    }

    /**
     * Gets the list of brand filters.
     *
     * @return the list of brand filters
     */

    public List<String> getBrandFilters() {
        return brandFilters;
    }

    /**
     * Gets the list of category filters.
     *
     * @return the list of category filters
     */
    public List<String> getCategoryFilters() {
        return categoryFilters;
    }

    /**
     * Gets the minimum price filter.
     *
     * @return the minimum price filter
     */
    public Float getMinPrice() {
        return minPrice;
    }

    /**
     * Gets the maximum price filter.
     *
     * @return the maximum price filter
     */
    public Float getMaxPrice() {
        return maxPrice;
    }

    /**
     * Filters the provided list of products based on the current filters and product name.
     *
     * @param products the list of products to filter
     * @param name the name to filter products by
     * @return the list of filtered products
     */
    public List<Product> filterProducts(List<Product> products, String name){
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if(!brandFilters.isEmpty()){
                if(!brandFilters.contains(product.getBrand())){
                    continue;
                }
            }

            if(!categoryFilters.isEmpty()){
                if(!categoryFilters.contains(product.getCategory())){
                    continue;
                }
            }

            if(minPrice != null && product.getPrice() < minPrice){
                continue;
            }

            if(maxPrice != null && product.getPrice() > maxPrice){
                continue;
            }

            if(!product.getTitle().contains(name)){
                continue;
            }

            filteredProducts.add(product);
        }

        return filteredProducts;
    }

    /**
     * Returns a string representation of the current filters.
     *
     * @return a string representation of the current filters
     */
    @Override
    public String toString() {
        String output = "";

        if(!brandFilters.isEmpty()){
            output += "Brands: " + brandFilters + "\n";
        }

        if(!categoryFilters.isEmpty()){
            output += "Categories: " + categoryFilters + "\n";
        }

        if(minPrice != null){
            output += "Min price: " + minPrice + "\n";
        }

        if(maxPrice != null){
            output += "Max price: " + maxPrice;
        }

        return output;
    }
}
