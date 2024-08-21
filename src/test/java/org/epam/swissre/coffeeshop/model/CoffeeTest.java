package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.CoffeeSize;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Coffee}.
 */
public class CoffeeTest {

    /**
     * Test the creation of Coffee with a valid size.
     */
    @Test
    public void testCoffeeCreation_ValidSize() {
        Coffee coffee = new Coffee(CoffeeSize.SMALL);
        assertNotNull(coffee, "Coffee should be created");
        assertEquals("Small coffee", coffee.getName(), "Coffee name should be 'Small coffee'");
        assertEquals(2.55, coffee.getPrice(), "Coffee price for small size should be 2.50 CHF");
    }
}
