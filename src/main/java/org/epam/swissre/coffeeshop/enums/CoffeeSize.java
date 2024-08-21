package org.epam.swissre.coffeeshop.enums;

public enum CoffeeSize {
    SMALL("Small coffee", 2.55),    // Small size coffee
    MEDIUM("Medium coffee", 3.05),  // Medium size coffee
    LARGE("Large coffee", 3.55);    // Large size coffee

    private final String displayName; // Descriptive name for the coffee size
    private final double priceInCHF;  // Price in CHF for the coffee size

    /**
     * Constructor for CoffeeSize enum.
     * @param displayName The human-readable name for the coffee size.
     * @param priceInCHF The price in Swiss Francs for this coffee size.
     */
    CoffeeSize(String displayName, double priceInCHF) {
        this.displayName = displayName;
        this.priceInCHF = priceInCHF;
    }

    /**
     * Retrieves the descriptive name of the coffee size.
     * @return the display name of the size.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Retrieves the price of the coffee size in Swiss Francs.
     * @return the price in CHF.
     */
    public double getPriceInCHF() {
        return priceInCHF;
    }
}