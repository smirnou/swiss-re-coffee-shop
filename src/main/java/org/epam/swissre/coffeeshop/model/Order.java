package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.OrderStatus;

import java.util.List;

/**
 * Represents an order in the coffee shop, which can contain multiple products.
 * This class manages the collection of products, and the various states and financial
 * attributes of an order, such as total cost and discounts applied.
 */
public class Order {
    private final List<Product> products;
    private OrderStatus status; // Assuming an enum exists for OrderStatus
    private double totalCost;
    private double totalDiscount;

    /**
     * Constructs a new Order with a specified list of products.
     * Sets the order status to OPEN upon creation.
     *
     * @param products A list of products that constitutes the order.
     */
    public Order(List<Product> products) {
        if (products == null) {
            throw new IllegalArgumentException("Products cannot be null");
        }
        this.products = products;
        this.status = OrderStatus.OPEN;
        this.totalCost = 0.0;
        this.totalDiscount = 0.0;
    }

    /**
     * Adds a product to the order.
     *
     * @param product The product to add to the order.
     */
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    /**
     * Provides the list of products in this order.
     *
     * @return An unmodifiable list of the products.
     */
    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    /**
     * Retrieves the current order status.
     *
     * @return The current status of the order.
     */
    public OrderStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status The new status of this order.
     */
    public void setStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Order status cannot be null");
        }
        this.status = status;
    }

    /**
     * Gets the total cost of the products in the order.
     *
     * @return The total cost of the products.
     */
    public double getTotalCost() {
        return this.totalCost;
    }

    /**
     * Sets the total cost of the order.
     *
     * @param totalCost The total cost to be set.
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Applies a discount to the order, adjusting the total cost.
     *
     * @param discount The discount amount to apply.
     */
    public void applyDiscount(double discount) {
        if (discount < 0) {
            throw new IllegalArgumentException("Discount cannot be negative");
        }
        this.totalDiscount += discount;
        this.totalCost -= discount;
    }

    /**
     * Retrieves the total discount applied to this order.
     *
     * @return The total discount amount.
     */
    public double getTotalDiscount() {
        return this.totalDiscount;
    }
}