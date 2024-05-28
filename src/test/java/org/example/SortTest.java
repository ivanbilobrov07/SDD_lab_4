package org.example;

import Product.Product;
import Sort.*;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SortTest {
    private SortByPrice sortByPrice;
    private SortByTitle sortByTitle;
    private Product product1, product2, product3;

    @BeforeEach
    void setUp() {
        sortByPrice = new SortByPrice();
        sortByTitle = new SortByTitle();

        product1 = new Product("1", "abc", 120, "description1", "brand1", "category1", 10, "image1");
        product2 = new Product("2", "cda", 100, "description2", "brand2", "category1", 10, "image2");
        product3 = new Product("3", "bca", 200, "description3", "brand3", "category2", 10, "image3");
    }

    @Test
    void testSortMultipleProductsByPrice() {
        List<Product> products = new ArrayList<Product>(Arrays.asList(product1, product2, product3));
        List<Product> sortedProducts = sortByPrice.sortProducts(products);

        assertEquals(product2, sortedProducts.get(0));
        assertEquals(product1, sortedProducts.get(1));
        assertEquals(product3, sortedProducts.get(2));
    }

    @Test
    void testSortEmptyListByPrice() {
        List<Product> products = new ArrayList<>();
        List<Product> sortedProducts = sortByPrice.sortProducts(products);
        assertTrue(sortedProducts.isEmpty());
    }

    @Test
    void testSortSingleProductByPrice() {
        List<Product> products = new ArrayList<>(Arrays.asList(product1));
        List<Product> sortedProducts = sortByPrice.sortProducts(products);

        assertEquals(1, sortedProducts.size());
        assertEquals(product1, sortedProducts.get(0));
    }

    @Test
    void testSortMultipleProductsByTitle() {
        List<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product3));
        List<Product> sortedProducts = sortByTitle.sortProducts(products);

        assertEquals(product1, sortedProducts.get(0));
        assertEquals(product2, sortedProducts.get(2));
        assertEquals(product3, sortedProducts.get(1));
    }

    @Test
    void testSortEmptyListByTitle() {
        List<Product> products = new ArrayList<>();
        List<Product> sortedProducts = sortByTitle.sortProducts(products);

        assertTrue(sortedProducts.isEmpty());
    }

    @Test
    void testSortSingleProductByTitle() {
        List<Product> products = new ArrayList<>(Arrays.asList(product1));
        List<Product> sortedProducts = sortByTitle.sortProducts(products);

        assertEquals(1, sortedProducts.size());
        assertEquals(product1, sortedProducts.get(0));
    }
}
