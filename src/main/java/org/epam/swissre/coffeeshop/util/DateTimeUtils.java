package org.epam.swissre.coffeeshop.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilities class for handling date and time operations.
 * This class provides static methods related to date and time operations that can be used
 * across various parts of an application. It centralizes common date and time operations
 * to ensure consistency and reduce code duplication.
 */
public class DateTimeUtils {

    /**
     * Returns the current date and time in the "yyyy-MM-dd HH:mm" format.
     * This method is helpful for generating a standardized date-time stamp string
     * for logging, displaying timestamps on UI, or other date-time related operations
     * within the application.
     *
     * @return A string formatted as "yyyy-MM-dd HH:mm" representing the current date and time.
     */
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return now.format(formatter);
    }
}
