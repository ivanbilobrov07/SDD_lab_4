package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {
    @Test
    void testValidateResponse_ValidInput() {
        assertTrue(Console.validateResponse("3", 5), "Input within range should return true");
    }

    @Test
    void testValidateResponse_BelowRange() {
        assertFalse(Console.validateResponse("0", 5), "Input below range should return false");
    }

    @Test
    void testValidateResponse_AboveRange() {
        assertFalse(Console.validateResponse("6", 5), "Input above range should return false");
    }

    @Test
    void testValidateResponse_NonIntegerInput() {
        assertFalse(Console.validateResponse("abc", 5), "Non-integer input should return false");
    }

    @Test
    void testValidateResponse_EmptyString() {
        assertFalse(Console.validateResponse("", 5), "Empty string should return false");
    }

    @Test
    void testValidatePrice_ValidWholeNumber() {
        assertTrue(Console.validatePrice("100"), "Whole number should be valid and non-negative");
    }

    @Test
    void testValidatePrice_ValidDecimal() {
        assertTrue(Console.validatePrice("150.50"), "Decimal number should be valid and non-negative");
    }

    @Test
    void testValidatePrice_Zero() {
        assertTrue(Console.validatePrice("0"), "Zero should be considered a valid non-negative price");
    }

    @Test
    void testValidatePrice_NegativePrice() {
        assertFalse(Console.validatePrice("-1"), "Negative price should be invalid");
    }

    @Test
    void testValidatePrice_NonNumericInput() {
        assertFalse(Console.validatePrice("abc"), "Non-numeric input should be invalid");
    }

    @Test
    void testValidatePrice_EmptyString() {
        assertFalse(Console.validatePrice(""), "Empty string should be invalid");
    }

    @Test
    void testValidateQuantity_ValidInteger() {
        assertTrue(Console.validateQuantity("10"), "A valid non-negative integer should be accepted");
    }

    @Test
    void testValidateQuantity_Zero() {
        assertTrue(Console.validateQuantity("0"), "Zero should be considered a valid non-negative integer");
    }

    @Test
    void testValidateQuantity_NegativeInteger() {
        assertFalse(Console.validateQuantity("-1"), "Negative integers should be considered invalid");
    }

    @Test
    void testValidateQuantity_NonNumericInput() {
        assertFalse(Console.validateQuantity("abc"), "Non-numeric input should be considered invalid");
    }

    @Test
    void testValidateQuantity_EmptyString() {
        assertFalse(Console.validateQuantity(""), "Empty string should be considered invalid");
    }
}
