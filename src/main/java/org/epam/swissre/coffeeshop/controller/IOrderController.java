package org.epam.swissre.coffeeshop.controller;

import org.epam.swissre.coffeeshop.model.Product;

import java.util.List;

/**
 * Interface for the Order Controller, responsible for managing orders.
 */
public interface IOrderController {
    void processOrder(List<Product> products);
}
