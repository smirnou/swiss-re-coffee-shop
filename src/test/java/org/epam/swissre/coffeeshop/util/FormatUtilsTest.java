package org.epam.swissre.coffeeshop.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link FormatUtilsTest}.
 */
public class FormatUtilsTest {

    @Test
    public void testFormatWithDotLeaders() {
        String description = "Espresso";
        double price = 3.50;
        String expectedResult = "Espresso..................................CHF 3,50";

        // Call the method
        String result = FormatUtils.formatWithDotLeaders(description, price);

        // Check that the result matches the expected value
        assertEquals(expectedResult, result, "The formatted string should match the expected layout with dot leaders.");

        // Additional tests can be added for different scenarios:
        // - Very long description that leaves little or no room for dots
        // - Handling of different price formats (e.g., very high prices, prices with many decimal places)
    }

    @Test
    public void testFormatWithDotLeaders_noDotsNeeded() {
        String description = "Long description that exceeds limit";
        double price = 10.25;
        String expectedResult = "Long description that exceeds limit......CHF 10,25"; // note no dots since length goes over limit

        // Call the method
        String result = FormatUtils.formatWithDotLeaders(description, price);

        // Check that the result matches the expected value
        assertEquals(expectedResult, result, "The formatted string should match even with no dots needed.");
    }
}
