package org.epam.swissre.coffeeshop.util;

public class FormatUtils {

    public static final int TOTAL_WIDTH = 50; // Define the total width for formatting (adjustable as needed)

    /**
     * Formats a line item with description and price, aligning the price to the right with dot leaders.
     * @param description The description of the item.
     * @param price The price of the item.
     * @return A formatted string with dot leaders.
     */
    public static String formatWithDotLeaders(String description, double price) {
        String priceStr = String.format("CHF %.2f", price);
        int numDots = TOTAL_WIDTH - description.length() - priceStr.length();
        return description + ".".repeat(Math.max(0, numDots)) + priceStr;
    }
}
