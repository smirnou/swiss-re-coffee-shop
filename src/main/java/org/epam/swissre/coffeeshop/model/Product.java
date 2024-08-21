package org.epam.swissre.coffeeshop.model;

import java.util.Objects;

/**
 * Abstract class representing a general product in the coffee shop.
 * This class is intended to be extended by specific product types.
 * Provides common properties like name, and price.
 */
public abstract class Product {
    private final String name;
    private final double price;

    /**
     * Constructs a new Product instance with specified name and price. This constructor
     * initializes the product with immutable values, providing thread safety and ensuring
     * that product details remain consistent throughout their lifecycle.
     *
     * @param name The name of the product, representing the identity or the type of product.
     *             This is expected to be a non-null and meaningful description.
     * @param price The price of the product, expected to be a non-negative value indicating
     *              the cost to the customer.
     */
    public Product(String name, double price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative.");
        }
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the product's name.
     * @return The name of the product which is a non-modifiable string.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the product's price.
     * @return The price of the product, provided as a positive double value.
     */
    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}