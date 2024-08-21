package org.epam.swissre.coffeeshop.enums;

public enum ExtraOption {
    EXTRA_MILK("Extra milk", 0.32),
    FOAMED_MILK("Foamed milk", 0.51),
    SPECIAL_ROAST_COFFEE("Special roast coffee", 0.95);

    private final String displayName;
    private final double priceInCHF;

    /**
     * Constructor for the ExtraOption enum.
     * @param displayName The human-readable name for the extra item.
     * @param priceInCHF The price in Swiss Francs for this extra item.
     */
    ExtraOption(String displayName, double priceInCHF) {
        this.displayName = displayName;
        this.priceInCHF = priceInCHF;
    }

    /**
     * Retrieves the descriptive name of the extra option.
     * @return the display name of the size.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Retrieves the price of the extra option in Swiss Francs.
     * @return the price in CHF.
     */
    public double getPriceInCHF() {
        return priceInCHF;
    }
}
