package org.epam.swissre.coffeeshop.service.impl;

import org.epam.swissre.coffeeshop.bonus.BonusStrategy;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.service.IBonusService;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the application of various promotional (bonus) strategies to orders.
 * This service allows for registration of multiple promotion (bonus) strategies
 * and applies all registered strategies to a given order.
 */
public class BonusService implements IBonusService {
    private boolean isApplyBonusCalled = false;
    private final List<BonusStrategy> bonusStrategies = new ArrayList<>();

    /**
     * Registers a promotion (bonus) strategy to be applied to orders.
     *
     * @param strategy The promotion (bonus) strategy to register.
     */
    @Override
    public void registerBonusStrategy(BonusStrategy strategy) {
        bonusStrategies.add(strategy);
    }

    /**
     * Applies all registered promotion (bonus) strategies to an order.
     *
     * @param order The order to which registered promotions are to be applied.
     */
    @Override
    public void applyBonus(Order order) {
        for (BonusStrategy strategy : bonusStrategies) {
            strategy.apply(order);
        }
        isApplyBonusCalled = true;
    }

    @Override
    public boolean isApplyBonusCalled() {
        return isApplyBonusCalled;
    }
}
