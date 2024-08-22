package org.epam.swissre.coffeeshop.input.impl;

import org.epam.swissre.coffeeshop.enums.CoffeeSize;
import org.epam.swissre.coffeeshop.input.ProductInput;
import org.epam.swissre.coffeeshop.model.Coffee;
import org.epam.swissre.coffeeshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Enhanced unit tests for CLIProductInputHandler.
 * These tests ensure that the CLI handler functions correctly under various simulated user inputs.
 */
public class CLIProductInputHandlerTest {

    private CLIProductInputHandler handler;
    private ProductInput productInput;

    /**
     * Sets up each test with a new instance of ProductInput and CLIProductInputHandler.
     * ProductInput is stubbed, and CLIProductInputHandler is configured with a simulated scanner.
     */
    @BeforeEach
    public void setUp() {
        productInput = new TestProductInput();
        productInput.addProduct(new Coffee(CoffeeSize.SMALL));
    }

    /**
     * Verifies that the correct coffee product is added to the order based on the user's size selection.
     */
    @Test
    public void testOrderCoffee() {
        String input = "1\n";  // Enter 1 to select "Small" size coffee.
        handler = new CLIProductInputHandler(productInput, new Scanner(new ByteArrayInputStream(input.getBytes())));
        handler.orderCoffee();

        Product expectedProduct = new Coffee(CoffeeSize.SMALL);
        assertTrue(productInput.getProducts().contains(expectedProduct), "Small coffee should be added to the order.");
    }

    /**
     * Checks the handler's ability to process integer inputs correctly and handle non-integer inputs gracefully.
     */
    @Test
    public void testIntInputHandlingWithErrorThenRecovery() {
        String input = "invalid\n2\n";
        handler = new CLIProductInputHandler(productInput, new Scanner(new ByteArrayInputStream(input.getBytes())));

        int validChoice = handler.getIntInputFromCLI("Please enter a number between 1 and 3.");
        assertEquals(2, validChoice, "The handler should parse the valid integer after an invalid entry.");
    }

    /**
     * Tests the order confirmation process by simulating a user confirming their order.
     */
    @Test
    public void testOrderConfirmationProcessing() {
        ByteArrayInputStream inConfirm = new ByteArrayInputStream("yes\n".getBytes());
        handler = new CLIProductInputHandler(productInput, new Scanner(inConfirm));

        handler.reviewOrder();
        assertTrue(productInput.isReadyToPay(), "Order should be marked as ready to pay after confirmation.");
    }

    /**
     * Test stub implementing ProductInput to simulate product management in tests.
     */
    static class TestProductInput implements ProductInput {
        private final List<Product> products = new ArrayList<>();
        private boolean readyToPay = false;

        @Override
        public void addProduct(Product product) {
            products.add(product);
        }

        @Override
        public List<Product> getProducts() {
            return products;
        }

        @Override
        public boolean isReadyToPay() {
            return readyToPay;
        }

        @Override
        public void setReadyToPay(boolean ready) {
            this.readyToPay = ready;
        }
    }
}