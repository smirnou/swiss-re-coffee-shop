package org.epam.swissre.coffeeshop.bonus.impl;

import org.epam.swissre.coffeeshop.bonus.BonusStrategy;
import org.epam.swissre.coffeeshop.enums.BaconRollSize;
import org.epam.swissre.coffeeshop.enums.CoffeeSize;
import org.epam.swissre.coffeeshop.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link FreeExtraWithBeverageAndSnackBonus}.
 */
public class FreeExtraWithBeverageAndSnackBonusTest {
    private BonusStrategy bonusStrategy;
    private Order order;

    @BeforeEach
    public void setUp() {
        bonusStrategy = new FreeExtraWithBeverageAndSnackBonus();
    }

    /**
     * Test that the bonus is applied when there is at least one beverage and one snack.
     */
    @Test
    public void testBonusApplied_WithBeverageAndSnack() {
        List<Product> products = new ArrayList<>();
        products.add(new Coffee(CoffeeSize.LARGE));
        products.add(new BaconRoll(BaconRollSize.STANDARD));
        products.add(new ExtraItem(org.epam.swissre.coffeeshop.enums.ExtraOption.EXTRA_MILK));

        order = new Order(products);
        bonusStrategy.apply(order);

        assertNotEquals(0, order.getTotalDiscount(), "Discount should not be zero when there's at least one beverage and snack");
    }

    /**
     * Test that the bonus is not applied when there is no beverage.
     */
    @Test
    public void testNoBonus_WithoutBeverage() {
        List<Product> products = new ArrayList<>();
        products.add(new BaconRoll(BaconRollSize.STANDARD));
        products.add(new ExtraItem(org.epam.swissre.coffeeshop.enums.ExtraOption.EXTRA_MILK));

        order = new Order(products);
        bonusStrategy.apply(order);

        assertEquals(0, order.getTotalDiscount(), "Discount should be zero if no beverage is present");
    }

    /**
     * Test that the bonus is not applied when there is no snack.
     */
    @Test
    public void testNoBonus_WithoutSnack() {
        List<Product> products = new ArrayList<>();
        products.add(new Coffee(CoffeeSize.LARGE));
        products.add(new ExtraItem(org.epam.swissre.coffeeshop.enums.ExtraOption.EXTRA_MILK));

        order = new Order(products);
        bonusStrategy.apply(order);

        assertEquals(0, order.getTotalDiscount(), "Discount should be zero if no snack is present");
    }

    /**
     * Test that the bonus is not applied when there are no extra items, even if there is a beverage and a snack.
     */
    @Test
    public void testNoBonus_WithoutExtraItem() {
        List<Product> products = new ArrayList<>();
        products.add(new Coffee(CoffeeSize.LARGE));
        products.add(new BaconRoll(BaconRollSize.STANDARD));

        order = new Order(products);
        bonusStrategy.apply(order);

        assertEquals(0, order.getTotalDiscount(), "Discount should be zero if no extra items are available for the bonus");
    }
}