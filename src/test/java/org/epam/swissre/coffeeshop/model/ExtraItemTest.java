package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.ExtraOption;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ExtraItem}.
 */
public class ExtraItemTest {

    /**
     * Test the creation of an ExtraItem with valid option.
     */
    @Test
    public void testExtraItemCreation_ValidOption() {
        ExtraItem extra = new ExtraItem(ExtraOption.EXTRA_MILK);
        assertNotNull(extra, "ExtraItem should be created");
        assertEquals("Extra milk", extra.getName(), "Extra item name should be 'Extra milk'");
        assertEquals(0.32, extra.getPrice(), "Extra milk price should be 0.30 CHF");
    }
}