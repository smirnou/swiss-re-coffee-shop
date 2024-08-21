package org.epam.swissre.coffeeshop.receipt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ReceiptRow}.
 */
public class ReceiptRowTest {

    /**
     * Tests the constructor for valid input, expecting no exceptions.
     */
    @Test
    public void testConstructor_validInput() {
        String description = "Coffee";
        double price = 3.50;
        ReceiptRow row = new ReceiptRow(description, price);
        assertNotNull(row, "ReceiptRow should be properly created with valid input.");
        assertEquals(description, row.getDescription(), "Description should be set correctly.");
        assertEquals(price, row.getPrice(), "Price should be set correctly.");
    }

    /**
     * Tests the constructor with a null description, expecting an IllegalArgumentException.
     */
    @Test
    public void testConstructor_nullDescription() {
        assertThrows(IllegalArgumentException.class, () -> new ReceiptRow(null, 3.50),
                "Constructor should throw IllegalArgumentException for null description.");
    }

    /**
     * Tests the constructor with an empty description, expecting an IllegalArgumentException.
     */
    @Test
    public void testConstructor_emptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> new ReceiptRow("", 3.50),
                "Constructor should throw IllegalArgumentException for empty description.");
    }

    /**
     * Tests the constructor with a negative price, expecting an IllegalArgumentException.
     */
    @Test
    public void testConstructor_negativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new ReceiptRow("Coffee Latte", -1.0),
                "Constructor should throw IllegalArgumentException for negative price.");
    }

    /**
     * Tests the setDescription method with a valid input.
     */
    @Test
    public void testSetDescription_validInput() {
        ReceiptRow row = new ReceiptRow("Coffee", 2.00);
        String newDescription = "Small";
        row.setDescription(newDescription);
        assertEquals(newDescription, row.getDescription(), "Description should be updated correctly.");
    }

    /**
     * Tests the setPrice method with a valid input.
     */
    @Test
    public void testSetPrice_validInput() {
        ReceiptRow row = new ReceiptRow("Coffee", 2.00);
        double newPrice = 2.50;
        row.setPrice(newPrice);
        assertEquals(newPrice, row.getPrice(), "Price should be updated correctly.");
    }
}
