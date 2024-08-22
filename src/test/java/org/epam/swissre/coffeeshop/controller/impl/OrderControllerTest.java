package org.epam.swissre.coffeeshop.controller.impl;

import org.epam.swissre.coffeeshop.controller.IOrderController;
import org.epam.swissre.coffeeshop.enums.OrderStatus;
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
    private ExceptionThrowingOrderStorage orderStorage;
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
        orderStorage = new ExceptionThrowingOrderStorage();
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
     * Tests the behavior of the OrderController when an IO-related exception is simulated during
     * order retrieval operations. This test ensures that the system appropriately catches and
     * handles runtime exceptions stemming from data retrieval failures.
     */
    @Test
    void testProcessOrder_RetrieveIOException() {
        orderStorage.setRetrieveThrowsIOException(true);

        List<Product> products = Arrays.asList(new TestProduct("Coffee", 2.50));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderController.processOrder(products);
        });

        assertEquals("Failed to access storage", exception.getMessage());
        assertFalse(orderService.isOrderProcessed(), "Order processing should not proceed after storage failure.");
    }

    /**
     * Tests the reaction of the OrderController when a RuntimeException is thrown during the
     * order storage operation. This is particularly to verify that the OrderController appropriately stops
     * further operations when an error is encountered in storing processed orders.
     */
    @Test
    void testProcessOrder_StoreIOException() {
        orderStorage.setStoreThrowsIOException(true);

        List<Product> products = Arrays.asList(new TestProduct("Coffee", 3.0));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderController.processOrder(products);
        });

        assertEquals("Failed to write to storage", exception.getMessage());
        assertTrue(orderService.isOrderProcessed(), "Order should be processed before storage issue");
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
            order.setStatus(OrderStatus.PAID);
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

    /**
     * A stub implementation of the IOrderStorage interface designed to simulate exceptional cases.
     * This class allows the caller to configure it to throw RuntimeExceptions during methods that
     * retrieve or store orders, emulating scenarios where an underlying storage system might encounter errors.
     */
    static class ExceptionThrowingOrderStorage implements IOrderStorage {
        private boolean retrieveThrowsIOException = false;
        private boolean storeThrowsIOException = false;

        /**
         * Configures the storage to throw a RuntimeException when retrieving orders.
         *
         * @param throwIOException Indicates whether to throw an exception during order retrieval.
         */
        void setRetrieveThrowsIOException(boolean throwIOException) {
            this.retrieveThrowsIOException = throwIOException;
        }

        /**
         * Configures the storage to throw a RuntimeException when storing orders.
         *
         * @param throwIOException Indicates whether to throw an exception during order storage.
         */
        void setStoreThrowsIOException(boolean throwIOException) {
            this.storeThrowsIOException = throwIOException;
        }

        @Override
        public List<Order> retrieveOrders() throws RuntimeException {
            if (retrieveThrowsIOException) {
                throw new RuntimeException("Failed to access storage");
            }
            return List.of(); // No orders to return.
        }

        @Override
        public void storeOrders(List<Order> orders) throws RuntimeException {
            if (storeThrowsIOException) {
                throw new RuntimeException("Failed to write to storage");
            }
        }
    }
}