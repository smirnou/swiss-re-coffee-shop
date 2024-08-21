package org.epam.swissre.coffeeshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Product}.
 */
public class ProductTest {

    static class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price);
        }
    }

    /**
     * Test the creation of a TestProduct with valid name and price.
     */
    @Test
    public void testProductCreation_ValidInput() {
        Product product = new TestProduct("Coffee", 2.99);
        assertNotNull(product, "Product should be created");
        assertEquals("Coffee", product.getName(), "Product name should match");
        assertEquals(2.99, product.getPrice(), "Product price should match");
    }

    /**
     * Test the creation of a TestProduct with a null name.
     */
    @Test
    public void testProductCreation_NullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TestProduct(null, 2.99));
        assertTrue(exception.getMessage().contains("Product name cannot be null or empty"));
    }

    /**
     * Test the creation of a TestProduct with a negative price.
     */
    @Test
    public void testProductCreation_NegativePrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TestProduct("Coffee", -1.00));
        assertTrue(exception.getMessage().contains("Product price cannot be negative"));
    }
}