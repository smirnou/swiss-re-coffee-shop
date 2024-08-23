package org.epam.swissre.coffeeshop.input.impl;

import org.epam.swissre.coffeeshop.input.ProductInput;
import org.epam.swissre.coffeeshop.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple in-memory implementation of the {@link ProductInput} interface.
 * This class stores {@link Product} objects in a list and provides methods to add
 * and retrieve products. This implementation is intended for demonstration and basic
 * use cases, such as testing and small-scale applications.
 *
 * <p>This class acts effectively as a temporary database directly in the program's memory,
 * and as such, all data will be lost when the application terminates. It is recommended to
 * replace this with a persistent data storage solution for production applications.</p>
 */
public class SimpleProductInput implements ProductInput {
    private final List<Product> products;
    private boolean readyToPay = false;

    /**
     * Constructs a new {@code SimpleProductInput} instance.
     */
    public SimpleProductInput() {
        this.products = new ArrayList<>();
    }

    /**
     * Adds a single {@link Product} object to the stored collection of products.
     * This method allows the addition of a product to the current session's product list.
     *
     * @param product the {@link Product} object to add; should not be {@code null}.
     * @throws IllegalArgumentException if the product is {@code null}.
     */
    @Override
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
        readyToPay = false; // the order must be confirmed separately
    }

    /**
     * Retrieves a list of all stored {@link Product} objects.
     * Since this implementation works with an in-memory list, it simply returns the list as is.
     *
     * @return a list of {@link Product}, not {@code null}, might be empty if no products have been added.
     */
    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy to prevent external modifications.
    }

    public boolean isReadyToPay() {
        return readyToPay;
    }

    public void setReadyToPay(boolean readyToPay) {
        this.readyToPay = readyToPay;
    }
}
