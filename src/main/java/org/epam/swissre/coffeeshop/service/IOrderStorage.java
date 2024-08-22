package org.epam.swissre.coffeeshop.service;

import org.epam.swissre.coffeeshop.model.Order;
import java.io.IOException;
import java.util.List;

/**
 * Interface for storing and retrieving coffee shop orders from a storage medium,
 * such as a CSV file.
 */
public interface IOrderStorage {

    /**
     * Stores a list of orders into a CSV file.
     * @param orders A list of orders to be stored.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    void storeOrders(List<Order> orders) throws IOException;

    /**
     * Retrieves orders from a CSV file.
     * @return A list of orders.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    List<Order> retrieveOrders() throws IOException;
}