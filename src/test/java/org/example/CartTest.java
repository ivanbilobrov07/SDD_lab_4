package org.example;

import Cart.*;
import Exceptions.EmptyException;
import Product.Product;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    void addProductTestsSetUp() {
        product1 = new Product("1", "Sample Product", 20, "some description", "some brand", "some category", 10 ,"image url");
    }

    void removeProductTestsAndChangeQuantityTestsSetUp() throws Exception {
        product1 = new Product("1", "Sample Product 1", 10, "some description 1", "some brand 1", "some category 1", 10 ,"image url 1");
        product2 = new Product("2", "Sample Product 2", 20, "some description 2", "some brand 2", "some category 2", 5 ,"image url 2");

        cart.addProduct(product1, 3);
        cart.addProduct(product2, 2);
    }

    @Test
    void testAddProduct_Successfully() throws Exception {
        addProductTestsSetUp();
        cart.addProduct(product1, 2);

        Product product = cart.getProducts().get(0).getProduct();
        int quantity = cart.getProducts().get(0).getQuantity();

        assertFalse(cart.isEmpty());
        assertEquals(1, cart.getProducts().size());
        assertEquals(40, cart.getTotalPrice());
        assertEquals(2, quantity);
        assertEquals("Sample Product", product.getTitle());
        assertEquals(20, product.getPrice());
        assertEquals(10, product.getQauntity());
        assertEquals("some brand", product.getBrand());
        assertEquals("some category", product.getCategory());
    }

    @Test
    void testAddProduct_ThrowsException_OverLimit() {
        addProductTestsSetUp();
        Exception exception = assertThrows(Exception.class, () -> {
            cart.addProduct(product1, 11);
        });
        assertTrue(exception.getMessage().contains("You can't add more than 10 items"));
    }

    @Test
    void testAddProduct_UpdateExistingProduct() throws Exception {
        addProductTestsSetUp();
        cart.addProduct(product1, 1);
        cart.addProduct(product1, 2);

        Product product = cart.getProducts().get(0).getProduct();
        int quantity = cart.getProducts().get(0).getQuantity();

        assertFalse(cart.isEmpty());
        assertEquals(1, cart.getProducts().size());
        assertEquals(3, quantity);
        assertEquals(60, cart.getTotalPrice());
        assertEquals("Sample Product", product.getTitle());
        assertEquals(20, product.getPrice());
        assertEquals(10, product.getQauntity());
        assertEquals("some brand", product.getBrand());
        assertEquals("some category", product.getCategory());
    }

    @Test
    void testAddProduct_MultipleAddsLeadingToOverLimit() {
        addProductTestsSetUp();
        assertDoesNotThrow(() -> cart.addProduct(product1, 5));
        Exception exception = assertThrows(Exception.class, () -> {
            cart.addProduct(product1, 6);
        });
        assertTrue(exception.getMessage().contains("You can't add more than 10 items"));
    }

    @Test
    void testRemoveProduct_ExistingProduct() throws Exception {
        removeProductTestsAndChangeQuantityTestsSetUp();

        CartProduct cartProduct1 = cart.getProducts().get(0);

        cart.removeProduct(cartProduct1);
        assertFalse(cart.getProducts().contains(cartProduct1));
        assertEquals(40, cart.getTotalPrice());
        assertEquals(cart.getProducts().size(), 1);
        assertFalse(cart.isEmpty());
    }

    @Test
    void testRemoveProduct_EmptyCart() throws Exception {
        removeProductTestsAndChangeQuantityTestsSetUp();

        CartProduct cartProduct1 = cart.getProducts().get(0);
        CartProduct cartProduct2 = cart.getProducts().get(1);

        cart.removeProduct(cartProduct1);
        cart.removeProduct(cartProduct2);

        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getTotalPrice());
    }

    @Test
    void testChangeQuantity_UpdateSuccessfully() throws Exception {
        removeProductTestsAndChangeQuantityTestsSetUp();

        CartProduct cartProduct1 = cart.getProducts().get(0);

        cart.changeQuantity(cartProduct1, 5);

        assertEquals(5, cartProduct1.getQuantity());
        assertEquals(90, cart.getTotalPrice());
        assertEquals(2, cart.getProducts().size());
    }

    @Test
    void testChangeQuantity_ToZero() throws Exception {
        removeProductTestsAndChangeQuantityTestsSetUp();

        CartProduct cartProduct1 = cart.getProducts().get(0);

        cart.changeQuantity(cartProduct1, 0);

        assertFalse(cart.getProducts().contains(cartProduct1));
        assertEquals(40, cart.getTotalPrice());
        assertEquals(1, cart.getProducts().size());
    }

    @Test
    void testChangeQuantity_InvalidQuantityThrowsError() throws Exception {
        removeProductTestsAndChangeQuantityTestsSetUp();

        CartProduct cartProduct1 = cart.getProducts().get(0);

        Exception exception = assertThrows(Exception.class, () -> {
            cart.changeQuantity(cartProduct1, 11);
        });
        assertTrue(exception.getMessage().contains("Incorrect quantity value"));
        assertEquals(3, cartProduct1.getQuantity());
        assertEquals(70, cart.getTotalPrice());
    }

    @Test
    void testChangeQuantity_NegativeQuantityThrowsError() throws Exception {
        removeProductTestsAndChangeQuantityTestsSetUp();

        CartProduct cartProduct1 = cart.getProducts().get(0);

        Exception exception = assertThrows(Exception.class, () -> {
            cart.changeQuantity(cartProduct1, -1);
        });
        assertTrue(exception.getMessage().contains("Incorrect quantity value"));
        assertEquals(3, cartProduct1.getQuantity());
        assertEquals(70, cart.getTotalPrice());
    }

    @Test
    void testLogCart_ThrowsEmptyException() {
        Exception exception = assertThrows(EmptyException.class, cart::logCart);
        assertEquals("The cart is empty", exception.getMessage());
    }


    @Test
    void testRemoveQuantityInProducts() throws Exception {
        removeProductTestsAndChangeQuantityTestsSetUp();
        cart.removeQuantityInProducts();

        assertEquals(7, product1.getQauntity());
        assertEquals(3, product2.getQauntity());
    }
}
