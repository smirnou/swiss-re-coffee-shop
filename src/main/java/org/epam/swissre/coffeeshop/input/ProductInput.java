package org.epam.swissre.coffeeshop.input;

import org.epam.swissre.coffeeshop.model.Product;

import java.util.List;

/**
 * The {@code ProductInput} interface is to define a standard contract for product data storage mechanisms.
 * Implementations of this interface handle the storage and retrieval of {@link Product} objects, which may be
 * provided by various input methods such as a user interface, a file (e.g., CSV), databases, or even external systems.
 *
 * <p>This interface facilitates abstraction of the specifics of storing product data away from the core business
 * logic of processing those products. This separation of concerns enhances modularity and allows changes in storage
 * methods without affecting other parts of the application. Additionally, it supports the gathering of product data
 * into a collective format that is easy to manage and utilize across different components of the system.</p>
 *
 * <p>The methods outlined herein ensure a consistent approach to adding new products to the system and retrieving
 * stored products.</p>
 */
public interface ProductInput {
    /**
     * Adds a single {@link Product} object to the underlying storage system.
     * This method is intended to store the product details in a manner appropriate
     * to the implemented storage system whether that be a database, a file, or in-memory storage.
     *
     * @param product the {@link Product} instance to add; must not be null.
     * @throws IllegalArgumentException if the product is null.
     */
    void addProduct(Product product);

    /**
     * Retrieves all the {@link Product} objects from the storage system.
     * This method should return a collection of all products that have been
     * added, consistent with how they were stored.
     *
     * @return a {@code List<Product>} containing all stored products. This list should
     * be a defensive copy if the underlying storage system uses collections to avoid unintended
     * modifications by external callers. May return an empty list if no products are stored but should
     * not return {@code null}.
     */
    List<Product> getProducts();

    boolean isReadyToPay();

    void setReadyToPay(boolean readyToPay);
}
