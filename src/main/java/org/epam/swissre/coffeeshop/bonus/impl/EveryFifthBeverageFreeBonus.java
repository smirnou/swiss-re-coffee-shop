package org.epam.swissre.coffeeshop.bonus.impl;

import org.epam.swissre.coffeeshop.bonus.BonusStrategy;
import org.epam.swissre.coffeeshop.model.*;

import java.util.List;
import java.util.stream.IntStream;

/**
 * A promotional strategy that offers every 5th beverage for free, consistently throughout the order.
 */
public class EveryFifthBeverageFreeBonus implements BonusStrategy {

    // Constant for how many beverages before a free one is given.
    private static final int FREE_BEVERAGE_INTERVAL = 5;

    /**
     * Applies the "Every Fifth Beverage Free" promotion to the order.
     * A discount equivalent to the price of every fifth beverage is applied.
     *
     * @param order The order to which the promotional logic will be applied.
     */
    @Override
    public void apply(Order order) {
        List<Product> beverages = getBeverages(order.getProducts());

        // Use IntStream to generate indices, filter by each fifth index, then map to corresponding beverage prices
        double totalDiscount = IntStream.range(0, beverages.size())
                .filter(index -> (index + 1) % FREE_BEVERAGE_INTERVAL == 0)
                .mapToDouble(index -> beverages.get(index).getPrice())
                .sum();

        order.applyDiscount(totalDiscount);
    }

    /**
     * Filters out beverages from a list of products.
     *
     * @param products The complete list of products from the order.
     * @return a list of products that are instances of beverages.
     */
    private List<Product> getBeverages(List<Product> products) {
        return products.stream().filter(i -> i instanceof Coffee || i instanceof OrangeJuice).toList();
    }
}