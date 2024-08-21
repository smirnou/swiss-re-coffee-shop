package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.OrangeJuiceSize;

/**
 * Represents a OrangeJuice product, extending the abstract Product class.
 * Includes additional properties such as size.
 */
public class OrangeJuice extends Product {

    /**
     * Constructs a new OrangeJuice instance including size information.
     * @param size the size of the orange juice (e.g., "Small")
     */
    public OrangeJuice(OrangeJuiceSize size) {
        super(size.getDisplayName(), size.getPriceInCHF());
    }
}