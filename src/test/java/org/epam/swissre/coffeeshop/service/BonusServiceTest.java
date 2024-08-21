package org.epam.swissre.coffeeshop.service;

import org.epam.swissre.coffeeshop.bonus.BonusStrategy;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.model.Product;
import org.epam.swissre.coffeeshop.service.impl.BonusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link BonusService}.
 */
public class BonusServiceTest {
    private BonusService bonusService;
    private TestBonusStrategy strategy1;
    private TestBonusStrategy strategy2;
    private Order testOrder;

    static class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price);
        }
    }

    @BeforeEach
    public void setUp() {
        Product product = new BonusServiceTest.TestProduct("Coffee", 10.0);
        bonusService = new BonusService();
        strategy1 = new TestBonusStrategy();
        strategy2 = new TestBonusStrategy();
        testOrder = new Order(Collections.singletonList(product));
    }

    /**
     * Test strategy application when multiple strategies are registered.
     */
    @Test
    public void testApplyBonus_StrategiesAreApplied() {
        bonusService.registerBonusStrategy(strategy1);
        bonusService.registerBonusStrategy(strategy2);

        bonusService.applyBonus(testOrder);

        assertTrue(strategy1.isApplied(), "Strategy 1 should have been applied");
        assertTrue(strategy2.isApplied(), "Strategy 2 should have been applied");
    }

    /**
     * Test strategy application when no strategies are registered.
     */
    @Test
    public void testApplyBonus_NoStrategiesRegistered() {
        bonusService.applyBonus(testOrder);
        // No strategies to apply, shouldn't change anything

        assertFalse(strategy1.isApplied(), "Strategy 1 should not have been applied");
        assertFalse(strategy2.isApplied(), "Strategy 2 should not have been applied");
    }

    /**
     * Concrete TestBonusStrategy for test purposes.
     */
    static class TestBonusStrategy implements BonusStrategy {
        private boolean applied = false;

        @Override
        public void apply(Order order) {
            applied = true;
            // For simplicity, the apply method is just flagging it was called
            order.applyDiscount(5.0);
        }

        public boolean isApplied() {
            return applied;
        }
    }
}