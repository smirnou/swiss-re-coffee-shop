package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.BaconRollSize;

/**
 * Represents a BaconRoll product, extending the abstract Product class.
 * Includes additional properties such as size.
 */
public class BaconRoll extends Product {

    /**
     * Constructs a new BaconRoll instance including size information.
     * @param size the size of the bacon roll (e.g., "Standard")
     */
    public BaconRoll(BaconRollSize size) {
        super(size.getDisplayName(), size.getPriceInCHF());
    }
}