package org.epam.swissre.coffeeshop.service.impl;

import org.epam.swissre.coffeeshop.bonus.BonusStrategy;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.model.Product;
import org.epam.swissre.coffeeshop.service.IBonusService;
import org.epam.swissre.coffeeshop.service.IOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link OrderService} class that handles order processing, including promotion application,
 * and price calculations. These tests ensure that the order service functions as expected across various scenarios.
 */
public class OrderServiceTest {
    private IOrderService orderService;
    private IBonusService stubBonusService;

    /**
     * Sets up instances for each test case. This method creates a stub bonus service (to simulate promotion
     * applications) and instantiates the OrderService with this stub service.
     */
    @BeforeEach
    public void setUp() {
        stubBonusService = new StubBonusService();
        orderService = new OrderService(stubBonusService);
    }

    /**
     * Verifies that the processOrder method correctly calculates the total price of all products in an order.
     */
    @Test
    public void testProcessOrder_CalculatesTotalPrice() {
        List<Product> products = Arrays.asList(new TestProduct("Coffee",10.0), new TestProduct("Orange Juice",15.0));
        Order processedOrder = orderService.processOrder(products, List.of());

        assertEquals(25.0, processedOrder.getTotalCost(), "Total cost should equal the sum of product prices.");
        assertTrue(stubBonusService.isApplyBonusCalled(), "applyBonus should be called on the bonus service.");
    }

    /**
     * Tests that processOrder throws the appropriate exception when provided with a null product list.
     */
    @Test
    public void testProcessOrder_WithNullProductList() {
        assertThrows(IllegalArgumentException.class, () -> orderService.processOrder(null, null),
                "Should throw IllegalArgumentException for null product list.");
    }

    /**
     * A stub implementation of the IBonusService interface for testing purposes.
     * This stub tracks whether its applyBonus method has been called.
     */
    static class StubBonusService implements IBonusService {
        private boolean isApplyBonusCalled = false;

        @Override
        public void registerBonusStrategy(BonusStrategy strategy) {
            // empty for this test scenario
        }

        /**
         * Applies a bonus to the order as part of the processing.
         * @param order The order to which the bonus is applied.
         */
        @Override
        public void applyBonus(Order order) {
            isApplyBonusCalled = true;
        }

        /**
         * Checks if the applyBonus method was called.
         * @return true if applyBonus was called, false otherwise.
         */
        @Override
        public boolean isApplyBonusCalled() {
            return isApplyBonusCalled;
        }
    }

    /**
     * Simple concrete product implementation for use in testing.
     * Represents a product with a price.
     */
    static class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price);
        }
    }
}