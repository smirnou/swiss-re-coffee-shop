package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.ExtraOption;

/**
 * Class representing an additional item (extra) that can be added to Product.
 * Contains properties such as name, and additional cost.
 */
public class ExtraItem extends Product {

    /**
     * Constructs a new Extra instance including extra option.
     * @param option the extra option (e.g., "Extra milk", "Foamed milk")
     */
    public ExtraItem(ExtraOption option) {
        super(option.getDisplayName(), option.getPriceInCHF());
    }
}
