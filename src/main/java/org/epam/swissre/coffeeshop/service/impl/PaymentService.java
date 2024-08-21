package org.epam.swissre.coffeeshop.service.impl;

import org.epam.swissre.coffeeshop.enums.OrderStatus;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.service.IPaymentService;

/**
 * Service class responsible for handling financial transactions related to orders. This class is pivotal
 * in ensuring that all payment-related activities align with the organization's financial policies and business rules.
 * PaymentService is a crucial component in the financial layer of the application, interfacing directly
 * with mechanisms that finalize the pricing structure established post-order calculations and promotional applications.
 */
public class PaymentService implements IPaymentService {

    /**
     * Processes the payment for an order based on the final calculated price post-discounts and promotions.
     * This method is crucial for finalizing the commercial transaction and ensuring accurate financial management.
     *
     * @param order The order for which the payment is being processed.
     */
    public void processPayment(Order order) {
        performPaymentTransaction(order);
    }

    /**
     * Simulates the process of executing a transaction. In a real-life scenario, this method would interface
     * with external payment providers to perform the financial transaction.
     *
     * @param order The order for which the payment is being processed.
     */
    private void performPaymentTransaction(Order order) {
        // Log or system print out for demonstration
        System.out.printf("Transaction completed. Amount charged: CHF %.2f\n", order.getTotalCost());

        order.setStatus(OrderStatus.PAID);
    }
}