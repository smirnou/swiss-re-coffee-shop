package org.epam.swissre.coffeeshop.enums;

public enum OrangeJuiceSize {
    SMALL("Fresh Orange Juice (0.25l)", 3.95);  // Example with one size, expandable with more sizes and prices

    private final String displayName; // Descriptive name for the orange juice size
    private final double priceInCHF;  // Price in CHF for the orange juice size

    /**
     * Constructor for the OrangeJuiceSize enum.
     * @param displayName The human-readable name for the orange juice size.
     * @param priceInCHF The price in Swiss Francs for this orange juice size.
     */
    OrangeJuiceSize(String displayName, double priceInCHF) {
        this.displayName = displayName;
        this.priceInCHF = priceInCHF;
    }

    /**
     * Retrieves the descriptive name of the orange juice size.
     * @return the display name of the size.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Retrieves the price of the orange juice size in Swiss Francs.
     * @return the price in CHF.
     */
    public double getPriceInCHF() {
        return priceInCHF;
    }
}
