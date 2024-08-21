package org.epam.swissre.coffeeshop.service;

import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.model.Product;

import java.util.List;

/**
 * Interface for the Order Service used to handle business logic related to order processing.
 */
public interface IOrderService {
    Order processOrder(List<Product> products);
}
