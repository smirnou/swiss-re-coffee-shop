package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.OrangeJuiceSize;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link OrangeJuice}.
 */
public class OrangeJuiceTest {

    /**
     * Test the creation of OrangeJuice with each valid size.
     */
    @Test
    public void testOrangeJuiceCreation_ValidSizes() {
        for (OrangeJuiceSize size : OrangeJuiceSize.values()) {
            OrangeJuice orangeJuice = new OrangeJuice(size);
            assertNotNull(orangeJuice, "Orange Juice should not be null");
            assertEquals(size.getDisplayName(), orangeJuice.getName(), "Orange Juice name should match the size's display name");
            assertEquals(size.getPriceInCHF(), orangeJuice.getPrice(), "Orange Juice price should be equal to the size's price in CHF");
        }
    }
}