package org.epam.swissre.coffeeshop.bonus.impl;

import org.epam.swissre.coffeeshop.bonus.BonusStrategy;
import org.epam.swissre.coffeeshop.enums.CoffeeSize;
import org.epam.swissre.coffeeshop.enums.OrangeJuiceSize;
import org.epam.swissre.coffeeshop.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link EveryFifthBeverageFreeBonus}.
 */
public class EveryFifthBeverageFreeBonusTest {
    private BonusStrategy bonusStrategy;
    private Order order;

    @BeforeEach
    public void setUp() {
        bonusStrategy = new EveryFifthBeverageFreeBonus();
    }

    /**
     * Test with an order containing exactly five beverages.
     */
    @Test
    public void testApplyBonus_ExactFiveBeverages() {
        List<Product> products = createBeveragesList(5); // Creating five beverages
        order = new Order(products);
        bonusStrategy.apply(order);

        double expectedDiscount = products.get(4).getPrice(); // Discount should match the price of the 5th beverage
        assertEquals(expectedDiscount, order.getTotalDiscount(), "Discount should equal the price of the fifth beverage");
    }

    /**
     * Test with an order containing multiple types of beverages, more than five but less than ten.
     */
    @Test
    public void testApplyBonus_MultipleTypesAndSixBeverages() {
        List<Product> products = createMixedBeveragesList(6); // Creating six mixed beverages
        order = new Order(products);
        bonusStrategy.apply(order);

        double expectedDiscount = products.get(4).getPrice(); // Only one beverage free
        assertEquals(expectedDiscount, order.getTotalDiscount(), "Discount should equal the price of the fifth beverage");
    }

    /**
     * Test with an order that has multiple instances of eligible free drinks.
     */
    @Test
    public void testApplyBonus_MultipleFreeBeverages() {
        List<Product> products = createBeveragesList(10); // Ten beverages
        order = new Order(products);
        bonusStrategy.apply(order);

        double expectedDiscount = products.get(4).getPrice() + products.get(9).getPrice();
        assertEquals(expectedDiscount, order.getTotalDiscount(), "Discount should be sum of the fifth and tenth beverages");
    }

    /**
     * Test that no discount is given if there are less than five beverages.
     */
    @Test
    public void testApplyBonus_InsufficientBeveragesForDiscount() {
        List<Product> products = createBeveragesList(4); // Only four beverages
        order = new Order(products);
        bonusStrategy.apply(order);

        assertEquals(0.0, order.getTotalDiscount(), "No discount should be applied if there are less than five beverages");
    }

    // Helper methods for creating beverage lists
    private List<Product> createBeveragesList(int count) {
        List<Product> beverages = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            beverages.add(new Coffee(CoffeeSize.SMALL)); // Adds small coffee
        }
        return beverages;
    }

    private List<Product> createMixedBeveragesList(int count) {
        List<Product> beverages = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                beverages.add(new Coffee(CoffeeSize.SMALL));
            } else {
                beverages.add(new OrangeJuice(OrangeJuiceSize.SMALL));
            }
        }
        return beverages;
    }
}
