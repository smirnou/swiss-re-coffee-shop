package org.epam.swissre.coffeeshop.service;

import org.epam.swissre.coffeeshop.model.Order;

/**
 * Interface defining the contract for payment processing services within the application.
 * This interface ensures that any payment processing class adheres to a standard
 * set of functionalities that are crucial for conducting financial transactions.
 */
public interface IPaymentService {
    /**
     * Processes the payment for an order. This method handles the financial transaction based
     * on the order's final calculated price after all discounts and promotions have been applied.
     *
     * @param order The order for which the payment needs to be processed.
     */
    void processPayment(Order order);
}
