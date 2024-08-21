package org.epam.swissre.coffeeshop.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DateTimeUtils}.
 */
public class DateTimeUtilsTest {

    /**
     * Test getCurrentDateTime returns the date in the correct format.
     * It does not validate the exact current time but checks the format of the output string.
     */
    @Test
    public void testGetCurrentDateTime() {
        String currentDateTime = DateTimeUtils.getCurrentDateTime();
        assertNotNull(currentDateTime, "Current date and time should not be null");

        // Create a DateTimeFormatter with the expected pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Test if the currentDateTime string can be parsed with the formatter (this will throw DateTimeParseException if not)
        // This indirectly checks if the string is in the expected format.
        assertDoesNotThrow(() -> LocalDateTime.parse(currentDateTime, formatter),
                "Current date and time string should be in the format yyyy-MM-dd HH:mm");

        // Optionally check the length of the string to match "yyyy-MM-dd HH:mm" (16 characters including the space)
        assertEquals(16, currentDateTime.length(), "Formatted date-time should be 16 characters long");
    }
}