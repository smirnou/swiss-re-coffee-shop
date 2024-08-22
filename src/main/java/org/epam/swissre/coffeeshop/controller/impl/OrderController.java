package org.epam.swissre.coffeeshop.controller.impl;

import org.epam.swissre.coffeeshop.controller.IOrderController;
import org.epam.swissre.coffeeshop.enums.OrderStatus;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.model.Product;
import org.epam.swissre.coffeeshop.receipt.ReceiptPresenter;
import org.epam.swissre.coffeeshop.receipt.ReceiptRow;
import org.epam.swissre.coffeeshop.service.IOrderService;
import org.epam.swissre.coffeeshop.service.IOrderStorage;
import org.epam.swissre.coffeeshop.service.IPaymentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The OrderController class orchestrates the processing of orders, calculation of payments,
 * and the presentation of receipts. It serves as a mediator between the service layer (order and
 * payment services) and the presentation layer (receipt presenter).
 */
public class OrderController implements IOrderController {
    private final IOrderService orderService;
    private final IOrderStorage orderStorage;
    private final IPaymentService paymentService;
    private final ReceiptPresenter receiptPresenter;

    /**
     * Constructs an OrderController with specified service and presenter components.
     *
     * @param orderService the service responsible for managing order details
     * @param orderStorage the service responsible for storing order data details
     * @param paymentService the service responsible for payment calculations
     * @param receiptPresenter the component responsible for presenting the receipt
     */
    public OrderController(IOrderService orderService,
                           IOrderStorage orderStorage,
                           IPaymentService paymentService,
                           ReceiptPresenter receiptPresenter) {
        this.orderService = orderService;
        this.orderStorage = orderStorage;
        this.paymentService = paymentService;
        this.receiptPresenter = receiptPresenter;
    }

    /**
     * Processes a list of products as an order. This method manages the complete order flow:
     * calculating the subtotal, final total (after taxes and discounts), and presenting the receipt.
     *
     * @param newProducts a list of products to be processed as an order
     */
    @Override
    public void processOrder(List<Product> newProducts) {
        List<Product> alreadyPaidProducts = new ArrayList<>();
        try {
            alreadyPaidProducts = orderStorage.retrieveOrders().stream()
                    .filter(Objects::nonNull) // Ensure that the order is not null
                    .flatMap(order -> order.getProducts() != null ? order.getProducts().stream() : Stream.empty()).toList(); // Safeguard against null product lists
        } catch (IOException e) {
            System.err.println("Failed to load paid orders. " + e.getMessage());
        }

        // Process the order through the OrderService to create an Order object
        Order order = orderService.processOrder(newProducts, alreadyPaidProducts);

        // Process the payment through the PaymentService
        paymentService.processPayment(order);

        try {
            if (order.getStatus() == OrderStatus.PAID) {
                orderStorage.storeOrders(List.of(order));
            }
        } catch (IOException e) {
            System.err.println("Failed to store paid order. " + e.getMessage());
        }

        makeReceipt(order); // output to CLI
    }

    private void makeReceipt(Order order) {
        // Use the ReceiptPresenter to add each product line item to the receipt
        for (Product product : order.getProducts()) {
            receiptPresenter.addReceiptRow(new ReceiptRow(product.getName(), product.getPrice()));
        }

        receiptPresenter.setTotalCost(order.getTotalCost());
        receiptPresenter.setTotalDiscount(order.getTotalDiscount());

        // Finally, present the receipt
        receiptPresenter.presentReceipt();
    }

}
