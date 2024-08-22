package org.epam.swissre.coffeeshop.service.impl;

import org.epam.swissre.coffeeshop.enums.CoffeeSize;
import org.epam.swissre.coffeeshop.model.Coffee;
import org.epam.swissre.coffeeshop.model.Order;
import org.epam.swissre.coffeeshop.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link OrderStorage}.
 */
public class OrderStorageTest {
    private OrderStorage orderStorage;
    private Path testFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        testFilePath = Files.createTempFile("testOrderStorage", ".csv");
        orderStorage = new OrderStorage(testFilePath.toString());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(testFilePath);
    }

    @Test
    public void testStoreAndRetrieveOrders() throws IOException {
        List<Order> expectedOrders = List.of(
                new Order(List.of(new Coffee(CoffeeSize.SMALL))),
                new Order(List.of(new Coffee(CoffeeSize.SMALL))),
                new Order(List.of(new Coffee(CoffeeSize.SMALL)))
        );

        // Storing orders to the file
        orderStorage.storeOrders(expectedOrders);

        // Retrieving orders from the file
        List<Order> actualOrders = orderStorage.retrieveOrders();

        // Assertions to ensure data is correctly written and read
        assertEquals(expectedOrders.size(), actualOrders.size(), "Number of orders should be equal");

        // Verify details of each recovered Order
        for (int i = 0; i < expectedOrders.size(); i++) {
            Order expected = expectedOrders.get(i);
            Order actual = actualOrders.get(i);
            assertEquals(expected.getProducts().size(), actual.getProducts().size(), "Number of products in order should be equal");
            for (int j = 0; j < expected.getProducts().size(); j++) {
                Product expProduct = expected.getProducts().get(j);
                Product actProduct = actual.getProducts().get(j);
                assertEquals(expProduct.getName(), actProduct.getName(), "Product names should match");
                assertEquals(expProduct.getPrice(), actProduct.getPrice(), "Product prices should match");
            }
        }
    }

    @Test
    public void testEmptyFile() throws IOException {
        // Ensuring that an empty file handles correctly
        List<Order> orders = orderStorage.retrieveOrders();
        assertTrue(orders.isEmpty(), "Retrieved list should be empty for a new file");
    }
}
