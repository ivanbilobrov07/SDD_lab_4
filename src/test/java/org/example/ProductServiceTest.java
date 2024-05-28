package org.example;

import Product.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    private ProductsService productsService;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        productsService = new ProductsService(null, null);
        product1 = new Product("1", "Sample Product 1", 10, "some description 1", "some brand 1", "some category 1", 10 ,"image url 1");
        product2 = new Product("2", "Sample Product 2", 20, "some description 2", "some brand 2", "some category 2", 5 ,"image url 2");
    }

    @Test
    void testAddValidProduct() {
        assertTrue(productsService.getProducts("").isEmpty());

        productsService.addProduct(product1);
        List<Product> products = productsService.getProducts("");

        assertTrue(products.contains(product1));
        assertEquals(1, products.size());
    }

    @Test
    void testAddMultipleProducts() {
        productsService.addProduct(product1);
        productsService.addProduct(product2);
        List<Product> products = productsService.getProducts("");

        assertTrue(products.containsAll(Arrays.asList(product1, product2)));
        assertEquals(2, products.size());
    }

    @Test
    void testRemoveProduct() {
        productsService.addProduct(product1);
        assertTrue(productsService.getProducts("").contains(product1));
        productsService.removeProduct(product1);

        assertFalse(productsService.getProducts("").contains(product1));
        assertEquals(0, productsService.getProducts("").size());
    }
}
