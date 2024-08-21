package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.CoffeeSize;

/**
 * Represents a Coffee product, extending the abstract Product class.
 * Includes additional properties such as size and a list of extras.
 */
public class Coffee extends Product {
    // TODO: private List<ExtraItem> extras; // Consider adding extras to the Coffee class  if needed, rather than adding Extra item directly to the Order List<Product>

    /**
     * Constructs a new Coffee instance including size information.
     * @param size the size of the coffee (e.g., "Small", "Medium", "Large")
     */
    public Coffee(CoffeeSize size) {
        super(size.getDisplayName(), size.getPriceInCHF());
    }
}