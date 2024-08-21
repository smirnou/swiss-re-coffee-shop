package org.epam.swissre.coffeeshop.service;

import org.epam.swissre.coffeeshop.bonus.BonusStrategy;
import org.epam.swissre.coffeeshop.model.Order;

/**
 * Defines the contract for services handling promotions and bonuses applied to orders.
 */
public interface IBonusService {

    /**
     * Registers a new promotional (bonus) strategy to be applied to orders.
     *
     * @param strategy The promotion (bonus) strategy to be registered.
     */
    void registerBonusStrategy(BonusStrategy strategy);

    /**
     * Applies all registered promotion strategies to a given order.
     *
     * @param order The order to which registered promotions will be applied.
     */
    void applyBonus(Order order);


    /**
     * Checks if the applyBonus method was called.
     * @return true if applyBonus was called, false otherwise.
     */
    boolean isApplyBonusCalled();
}
