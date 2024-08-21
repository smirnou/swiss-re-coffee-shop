package org.epam.swissre.coffeeshop.controller.impl;

import org.epam.swissre.coffeeshop.controller.IOrderController;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.model.Product;
import org.epam.swissre.coffeeshop.receipt.ReceiptPresenter;
import org.epam.swissre.coffeeshop.receipt.ReceiptRow;
import org.epam.swissre.coffeeshop.service.IOrderService;
import org.epam.swissre.coffeeshop.service.IPaymentService;

import java.util.List;

/**
 * The OrderController class orchestrates the processing of orders, calculation of payments,
 * and the presentation of receipts. It serves as a mediator between the service layer (order and
 * payment services) and the presentation layer (receipt presenter).
 */
public class OrderController implements IOrderController {
    private final IOrderService orderService;
    private final IPaymentService paymentService;
    private final ReceiptPresenter receiptPresenter;

    /**
     * Constructs an OrderController with specified service and presenter components.
     *
     * @param orderService the service responsible for managing order details
     * @param paymentService the service responsible for payment calculations
     * @param receiptPresenter the component responsible for presenting the receipt
     */
    public OrderController(IOrderService orderService,
                           IPaymentService paymentService,
                           ReceiptPresenter receiptPresenter) {
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.receiptPresenter = receiptPresenter;
    }

    /**
     * Processes a list of products as an order. This method manages the complete order flow:
     * calculating the subtotal, final total (after taxes and discounts), and presenting the receipt.
     *
     * @param products a list of products to be processed as an order
     */
    @Override
    public void processOrder(List<Product> products) {
        // Process the order through the OrderService to create an Order object
        Order order = orderService.processOrder(products);

        // Process the payment through the PaymentService
        paymentService.processPayment(order);

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
