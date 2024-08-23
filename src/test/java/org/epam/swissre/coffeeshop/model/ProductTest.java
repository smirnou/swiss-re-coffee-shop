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

    /**
     * Test the equality of two Products with identical name and price.
     */
    @Test
    public void testEquality_SameNameAndPrice() {
        Product product1 = new TestProduct("Coffee", 2.99);
        Product product2 = new TestProduct("Coffee", 2.99);
        assertTrue(product1.equals(product2), "Identical products should be equal");
        assertEquals(product1.hashCode(), product2.hashCode(), "Hashcodes should be identical for equal objects");
    }

    /**
     * Test the inequality of two Products with different names.
     */
    @Test
    public void testEquality_DifferentNames() {
        Product product1 = new TestProduct("Small coffee", 2.99);
        Product product2 = new TestProduct("Large coffee", 2.99);
        assertFalse(product1.equals(product2), "Products with different names should not be equal");
    }

    /**
     * Test the inequality of two Products with different prices.
     */
    @Test
    public void testEquality_DifferentPrices() {
        Product product1 = new TestProduct("Coffee", 2.99);
        Product product2 = new TestProduct("Coffee", 3.99);
        assertFalse(product1.equals(product2), "Products with different prices should not be equal");
    }

    /**
     * Test product equality against a null reference.
     */
    @Test
    public void testEquality_Null() {
        Product product = new TestProduct("Coffee", 2.99);
        assertFalse(product.equals(null), "Product should not be equal to null");
    }

    /**
     * Test product equality against an object of a different type.
     */
    @Test
    public void testEquality_DifferentClass() {
        Product product = new TestProduct("Coffee", 2.99);
        Object otherObject = new Object();
        assertFalse(product.equals(otherObject), "Product should not be equal to an object of a different type");
    }
}