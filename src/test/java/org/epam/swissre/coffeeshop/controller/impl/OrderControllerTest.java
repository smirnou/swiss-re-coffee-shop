package org.epam.swissre.coffeeshop.controller.impl;

import org.epam.swissre.coffeeshop.controller.IOrderController;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.model.Product;
import org.epam.swissre.coffeeshop.receipt.ReceiptPresenter;
import org.epam.swissre.coffeeshop.service.IOrderService;
import org.epam.swissre.coffeeshop.service.IOrderStorage;
import org.epam.swissre.coffeeshop.service.IPaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the {@link OrderController} class to ensure integration of components
 * like {@link IOrderService}, {@link IPaymentService}, and {@link ReceiptPresenter}
 * works as expected when processing an order.
 */
class OrderControllerTest {
    private IOrderController orderController;
    private StubOrderService orderService;
    private StubOrderStorage orderStorage;
    private StubPaymentService paymentService;
    private TestableReceiptPresenter receiptPresenter;

    static class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price);
        }
    }

    /**
     * Sets up each test with fresh instances of the controller and mocked services.
     */
    @BeforeEach
    void setUp() {
        orderService = new StubOrderService();
        orderStorage = new StubOrderStorage();
        paymentService = new StubPaymentService();
        receiptPresenter = new TestableReceiptPresenter();
        orderController = new OrderController(orderService, orderStorage, paymentService, receiptPresenter);
    }

    /**
     * Verifies that the processOrder method successfully orchestrates the order process,
     * payment processing, and receipt presentation.
     */
    @Test
    void testProcessOrder() {
        Product product1 = new TestProduct("Coffee", 2.50);
        Product product2 = new TestProduct("Orange Juice", 1.50);
        orderController.processOrder(Arrays.asList(product1, product2));

        assertTrue(orderService.isOrderProcessed(), "Order service should process the order.");
        assertTrue(paymentService.isPaymentProcessed(), "Payment service should process the payment.");
        assertTrue(receiptPresenter.isReceiptPresented(), "Receipt should be presented.");
    }

    /**
     * Stub implementation of the {@link IOrderService} to simulate order processing.
     */
    static class StubOrderService implements IOrderService {
        private boolean orderProcessed = false;

        @Override
        public Order processOrder(List<Product> newProducts, List<Product> alreadyPaidProducts) {
            orderProcessed = true;
            return new Order(newProducts); // Simulate an order
        }

        boolean isOrderProcessed() {
            return orderProcessed;
        }
    }

    /**
     * Stub implementation of the {@link IOrderStorage} to simulate storing data.
     */
    static class StubOrderStorage implements IOrderStorage {

        @Override
        public void storeOrders(List<Order> orders) {
            // stub is empty
        }

        @Override
        public List<Order> retrieveOrders() {
            return List.of();
        }
    }

    /**
     * Stub implementation of the {@link IPaymentService} to simulate payment processing.
     */
    static class StubPaymentService implements IPaymentService {
        private boolean paymentProcessed = false;

        @Override
        public void processPayment(Order order) {
            paymentProcessed = true;
        }

        boolean isPaymentProcessed() {
            return paymentProcessed;
        }
    }

    /**
     * A testable extension of {@link ReceiptPresenter} that allows verification of whether the receipt
     * has been presented.
     */
    static class TestableReceiptPresenter extends ReceiptPresenter {
        private boolean receiptPresented = false;

        @Override
        public void presentReceipt() {
            receiptPresented = true; // Mark that the receipt was presented
        }

        boolean isReceiptPresented() {
            return receiptPresented;
        }
    }
}