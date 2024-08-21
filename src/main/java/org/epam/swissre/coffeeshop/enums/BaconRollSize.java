package org.epam.swissre.coffeeshop.enums;

public enum BaconRollSize {
    STANDARD("Standard Bacon Roll", 4.53);  // Standard size

    private final String displayName;
    private final double priceInCHF;

    /**
     * Constructor for BaconRollSize enum.
     * @param displayName The human-readable name for the bacon roll size.
     * @param priceInCHF The price in Swiss Francs for this bacon roll size.
     */
    BaconRollSize(String displayName, double priceInCHF) {
        this.displayName = displayName;
        this.priceInCHF = priceInCHF;
    }

    /**
     * Retrieves the descriptive name of the bacon roll size.
     * @return the display name of the size.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Retrieves the price of the bacon roll size in Swiss Francs.
     * @return the price in CHF.
     */
    public double getPriceInCHF() {
        return priceInCHF;
    }
}