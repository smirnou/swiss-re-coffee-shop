package org.epam.swissre.coffeeshop.bonus.impl;

import org.epam.swissre.coffeeshop.bonus.BonusStrategy;
import org.epam.swissre.coffeeshop.model.*;

import java.util.List;
import java.util.Random;

/**
 * A promotional strategy that provides a specific free extra item
 * when a customer purchases at least one beverage and one snack.
 * This promotion assumes a specific predefined extra item (e.g., Extra Milk)
 * that is added to the order for free under qualifying conditions.
 */
public class FreeExtraWithBeverageAndSnackBonus implements BonusStrategy {

    final Random random = new Random();

    /**
     * Applies the promotion by adding a free Extra Item to the order if it contains
     * at least one beverage and one snack.
     *
     * @param order The order to which the promotional logic will be applied.
     */
    @Override
    public void apply(Order order) {
        final List<Product> products = order.getProducts();
        boolean hasBeverage = hasBeverage(products);
        boolean hasSnack = hasSnack(products);

        if (hasBeverage && hasSnack) {
            final List<Product> extraItems = getExtraItems(products);
            if (!extraItems.isEmpty()) {
                Product freeExtra = selectRandomExtraItem(extraItems);
                // add extra price as discount
                order.applyDiscount(freeExtra.getPrice());
            } /* else the discount is not provided because product list does not include beverage and snacks
                (in the actual production code you can add a logger to log the exception case) */

        } /* else the discount is not provided because there are no beverage and snacks in the product list
            (in the actual production code you can add a logger to log the exception case) */
    }

    private boolean hasBeverage(List<Product> products) {
        return products.stream().anyMatch(i -> i instanceof Coffee || i instanceof OrangeJuice);
    }

    private boolean hasSnack(List<Product> products) {
        return products.stream().anyMatch(i -> i instanceof BaconRoll);
    }

    private List<Product> getExtraItems(List<Product> products) {
        return products.stream().filter(i -> i instanceof ExtraItem).toList();
    }

    // Revised method to select a random extra item correctly
    private Product selectRandomExtraItem(List<Product> extras) {
        int index = random.nextInt(extras.size()); // Safe now since you check isEmpty() prior
        return extras.get(index);
    }
}
