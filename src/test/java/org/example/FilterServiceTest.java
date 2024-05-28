package org.example;

import Filter.*;
import Product.Product;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterServiceTest {
    private FilterService filterService;
    List<Product> products;

    @BeforeEach
    void setUp() {
        filterService = new FilterService();
    }

    void brandAndCategoriesSetUp(){
        filterService.addBrandToFilter("Milka");
        filterService.addBrandToFilter("Roshen");
        filterService.addCategoryFilter("Sweets");
        filterService.addCategoryFilter("Vegetables");
    }

    void allFiltersSetUp(){
        filterService.addBrandToFilter("Milka");
        filterService.addCategoryFilter("Sweets");
        filterService.changePriceFilters(FilterType.MIN_PRICE, 10f);
        filterService.changePriceFilters(FilterType.MAX_PRICE, 100f);
    }

    void filterProductsSetUp(){
        products = Arrays.asList(
                new Product("1", "title1", 120, "description1", "brand1", "category1", 10, "image1"),
                new Product("2", "title2", 100, "description2", "brand2", "category1", 10, "image2"),
                new Product("3", "title3", 200, "description3", "brand3", "category2", 10, "image3")
        );
    }

    @Test
    void testAddSingleBrand() {
        filterService.addBrandToFilter("Milka");

        assertFalse(filterService.getBrandFilters().isEmpty());
        assertTrue(filterService.getBrandFilters().contains("Milka"));
        assertEquals(1, filterService.getBrandFilters().size());
    }

    @Test
    void testAddMultipleUniqueBrands() {
        filterService.addBrandToFilter("Milka");
        filterService.addBrandToFilter("Roshen");

        assertEquals(2, filterService.getBrandFilters().size());
        assertTrue(filterService.getBrandFilters().contains("Milka"));
        assertTrue(filterService.getBrandFilters().contains("Roshen"));
    }

    @Test
    void testAddDuplicateBrands() {
        filterService.addBrandToFilter("Milka");
        filterService.addBrandToFilter("Milka");

        assertEquals(1, filterService.getBrandFilters().size());
    }

    @Test
    void testAddSingleCategory() {
        filterService.addCategoryFilter("Sweets");

        assertFalse(filterService.getCategoryFilters().isEmpty());
        assertTrue(filterService.getCategoryFilters().contains("Sweets"));
        assertEquals(1, filterService.getCategoryFilters().size());
    }

    @Test
    void testAddMultipleUniqueCategories() {
        filterService.addCategoryFilter("Sweets");
        filterService.addCategoryFilter("Vegetables");

        assertEquals(2, filterService.getCategoryFilters().size());
        assertTrue(filterService.getCategoryFilters().contains("Sweets"));
        assertTrue(filterService.getCategoryFilters().contains("Vegetables"));
    }

    @Test
    void testAddDuplicateCategories() {
        filterService.addCategoryFilter("Sweets");
        filterService.addCategoryFilter("Sweets");

        assertEquals(1, filterService.getCategoryFilters().size());
    }

    @Test
    void testChangePriceFilter_SetMinPrice() {
        filterService.changePriceFilters(FilterType.MIN_PRICE, 100f);

        assertEquals(100, filterService.getMinPrice());
    }

    @Test
    void testChangePriceFilter_SetMaxPrice() {
        filterService.changePriceFilters(FilterType.MAX_PRICE, 200f);

        assertEquals(200, filterService.getMaxPrice());
    }

    @Test
    void testChangePriceFilters_BothFilters() {
        filterService.changePriceFilters(FilterType.MIN_PRICE, 50f);
        filterService.changePriceFilters(FilterType.MAX_PRICE, 150f);

        assertEquals(50, filterService.getMinPrice());
        assertEquals(150, filterService.getMaxPrice());
    }

    @Test
    void testChangePriceFilters_NullValues() {
        filterService.changePriceFilters(FilterType.MIN_PRICE, null);
        filterService.changePriceFilters(FilterType.MAX_PRICE, null);

        assertNull(filterService.getMinPrice());
        assertNull(filterService.getMaxPrice());
    }

    @Test
    void testClearBrandFilters() {
        brandAndCategoriesSetUp();
        filterService.clearAllArrayFilters(FilterType.BRAND);

        assertTrue(filterService.getBrandFilters().isEmpty());
        assertFalse(filterService.getCategoryFilters().isEmpty());
    }

    @Test
    void testClearCategoryFilters() {
        brandAndCategoriesSetUp();
        filterService.clearAllArrayFilters(FilterType.CATEGORY);

        assertTrue(filterService.getCategoryFilters().isEmpty());
        assertFalse(filterService.getBrandFilters().isEmpty());
    }

    @Test
    void testClearAllFilters() {
        allFiltersSetUp();
        filterService.clearAllFilters();

        assertTrue(filterService.getBrandFilters().isEmpty());
        assertTrue(filterService.getCategoryFilters().isEmpty());
        assertNull(filterService.getMinPrice());
        assertNull(filterService.getMaxPrice());
    }

    @Test
    void testFilterByBrand() {
        filterProductsSetUp();
        filterService.addBrandToFilter("brand1");

        List<Product> filteredProducts = filterService.filterProducts(products, "");

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.get(0).getBrand().equals("brand1"));
    }

    @Test
    void testFilterByCategory() {
        filterProductsSetUp();
        filterService.addCategoryFilter("category1");

        List<Product> filteredProducts = filterService.filterProducts(products, "");

        assertEquals(2, filteredProducts.size());
        assertTrue(filteredProducts.stream().allMatch(p -> p.getCategory().equals("category1")));
    }

    @Test
    void testFilterByPriceRange() {
        filterProductsSetUp();
        filterService.changePriceFilters(FilterType.MIN_PRICE, 110f);
        filterService.changePriceFilters(FilterType.MAX_PRICE, 180f);

        List<Product> filteredProducts = filterService.filterProducts(products, "");

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.get(0).getPrice() <= 180f && filteredProducts.get(0).getPrice() >= 110f);
    }

    @Test
    void testFilterByProductName() {
        filterProductsSetUp();

        List<Product> filteredProducts = filterService.filterProducts(products, "1");

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.get(0).getTitle().contains("title1"));
    }

    @Test
    void testNoFilters() {
        filterProductsSetUp();

        List<Product> filteredProducts = filterService.filterProducts(products, "");

        assertEquals(3, filteredProducts.size());
    }

    @Test
    void testCombinedFilters() {
        filterProductsSetUp();

        filterService.addBrandToFilter("brand1");
        filterService.changePriceFilters(FilterType.MAX_PRICE, 150.0f);

        List<Product> filteredProducts = filterService.filterProducts(products, "1");

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.get(0).getBrand().equals("brand1") && filteredProducts.get(0).getTitle().contains("title1"));
    }
}
