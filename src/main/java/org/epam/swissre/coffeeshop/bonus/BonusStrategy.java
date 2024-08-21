package org.epam.swissre.coffeeshop.bonus;

import org.epam.swissre.coffeeshop.model.Order;

/**
 * The interface defines a contract for promotional (bonus) strategies
 * that can be applied to orders in a coffee shop context.
 */
public interface BonusStrategy {
    /**
     * Apply a promotional (bonus) strategy to a given order.
     *
     * @param order The order to which the promotional (bonus) logic will be applied.
     */
    void apply(Order order);
}
