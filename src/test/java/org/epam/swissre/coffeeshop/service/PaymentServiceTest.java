package org.epam.swissre.coffeeshop.service;

import org.epam.swissre.coffeeshop.enums.OrderStatus;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.service.impl.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PaymentService}.
 */
public class PaymentServiceTest {
    private IPaymentService paymentService;
    private Order order;

    @BeforeEach
    public void setUp() {
        paymentService = new PaymentService();
        order = new Order(new ArrayList<>()); // dummy order with empty products
        order.setTotalCost(100.00);  // Setting total cost
        order.applyDiscount(20.00);  // Applying a discount
    }

    /**
     * Test that processPayment appropriately sets the order status PAID.
     */
    @Test
    public void testProcessPayment_SetsOrderStatusToPaid() {
        assertEquals(OrderStatus.OPEN, order.getStatus(), "Order status should be OPEN before processing payment.");
        paymentService.processPayment(order);
        assertEquals(OrderStatus.PAID, order.getStatus(), "Order status should be set to PAID after processing payment.");
    }

    /**
     * Test to ensure totalCost remains unchanged after payment processing.
     */
    @Test
    public void testTotalCostUnchangedAfterPayment() {
        double initialTotalCost = order.getTotalCost();
        paymentService.processPayment(order);
        assertEquals(initialTotalCost, order.getTotalCost(), "Total cost should remain unchanged after payment processing.");
    }

    /**
     * Test to verify that the total discount is applied correctly and maintained after payment processing.
     */
    @Test
    public void testTotalDiscountUnchangedAfterPayment() {
        double initialDiscount = order.getTotalDiscount();
        paymentService.processPayment(order);
        assertEquals(initialDiscount, order.getTotalDiscount(), "Total discount should remain unchanged after payment processing.");
    }
}
