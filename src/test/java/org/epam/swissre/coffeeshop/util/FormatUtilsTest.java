package org.epam.swissre.coffeeshop.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link FormatUtils}.
 * This class tests the functionality of formatting strings with dot leaders.
 */
public class FormatUtilsTest {

    /**
     * Tests the formatWithDotLeaders method when dots are expected to be included in the formatted string.
     */
    @Test
    public void testFormatWithDotLeaders() {
        String description = "Espresso";
        double price = 3.50;
        int expectedSizeResult = FormatUtils.TOTAL_WIDTH;

        // Call the method
        int result = FormatUtils.formatWithDotLeaders(description, price).length();

        // Check that the length of the result matches the expected fixed width
        assertEquals(expectedSizeResult, result, "The length of the formatted string should match the expected fixed total width, including dot leaders.");
    }

    /**
     * Tests the formatWithDotLeaders method when the description is so long that no dots are needed between the description and the price.
     */
    @Test
    public void testFormatWithDotLeaders_Long() {
        String description = "Long description that exceeds limit";
        double price = 10.25;
        int expectedSizeResult = FormatUtils.TOTAL_WIDTH;

        // Call the method
        int result = FormatUtils.formatWithDotLeaders(description, price).length();

        // Check that the result matches the expected value
        assertEquals(expectedSizeResult, result, "The length of the formatted string should equal the expected fixed total width, even when no dots are needed.");
    }
}