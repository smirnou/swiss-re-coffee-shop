package org.epam.swissre.coffeeshop.service.impl;

import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.model.Product;
import org.epam.swissre.coffeeshop.service.IBonusService;
import org.epam.swissre.coffeeshop.service.IOrderService;

import java.util.List;

/**
 * Service class responsible for handling orders and their related operations,
 * including applying any promotional strategies prior to finalizing the order total.
 * The class interfaces with BonusService to apply promotions and calculates total
 * prices depending on the result of those promotions.
 * OrderService serves as a critical component in the business logic layer of the application,
 * managing the retrieval, processing, and aggregation of order data to ensure that all order-related actions
 * are executed based on the defined business rules.
 */
public class OrderService implements IOrderService {

    private final IBonusService bonusService;

    /**
     * Constructor for OrderService.
     *
     * @param bonusService The bonus service to apply promotions.
     */
    public OrderService(IBonusService bonusService) {
        this.bonusService = bonusService;
    }

    /**
     * Processes an order, starting by applying applicable promotions from the bonus service,
     * then calculating the total price of all included products. This method also checks
     * additional business rules related to discounts and bonuses.
     *
     * @param newProducts The list of products in the order.
     * @return A processed Order instance containing details on the total price, eligibility for promotions.
     */
    @Override
    public Order processOrder(List<Product> newProducts, List<Product> alreadyPaidProducts) {
        if (newProducts == null || newProducts.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be null or empty.");
        }

        Order order = new Order(newProducts);
        if (!alreadyPaidProducts.isEmpty()) {
            order.setAlreadyPaidProducts(alreadyPaidProducts); // for the next calculation of discount (bonus)
        }

        try {
            // Calculate total price after promotions might have modified the order
            double totalPrice = calculateTotalPrice(order.getProducts());
            order.setTotalCost(totalPrice);

            // Apply promotions (bonuses)
            bonusService.applyBonus(order);

            // Here you might want to apply other business rules or further adjustments
            return order;
        } catch (Exception e) {
            throw new RuntimeException("Failed to process the order.", e);
        }
    }

    /**
     * Calculates the total price of products included in an order.
     * This is the gross total before any additional adjustments such as taxes.
     *
     * @param products A list of Product instances representing the items in an order.
     * @return The cumulative price of all products, represented as a double value.
     */
    private double calculateTotalPrice(List<Product> products) {
        if (products == null) {
            throw new IllegalArgumentException("Product list cannot be null");
        }
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
