package org.epam.swissre.coffeeshop.service.impl;

import org.epam.swissre.coffeeshop.model.*;
import org.epam.swissre.coffeeshop.service.IOrderStorage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the storage and retrieval of Orders, specifically the products within an order,
 * to and from a CSV file. It ensures proper file handling to facilitate multiple successive
 * application executions without data loss or corruption.
 */
public class OrderStorage implements IOrderStorage {

    private final String filePath;

    /**
     * Constructs an OrderStorage object linked to a specified file path. If the CSV file does not exist,
     * it attempts to create a new one.
     *
     * @param filePath the path where the CSV file is stored or will be created.
     */
    public OrderStorage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        try {
            // Attempt to create the file if it does not already exist
            boolean isNewFileCreated = file.createNewFile();
            if (isNewFileCreated) {
                // Log or handle the case where a new file had to be created
                System.out.println("A new file was successfully created at: " + filePath);
            } else {
                // The file already exists, you might want to log this info
                System.out.println("File already exists at: " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize storage file.", e);
        }
    }

    /**
     * Stores new orders in the CSV file by appending them after existing orders. It first reads
     * the current file contents, adds the new orders, and rewrites the combined list into the file
     * to maintain continuity across multiple application runs.
     *
     * @param newOrders a list of new orders to be appended to the file.
     * @throws IOException if an error occurs during file operation.
     */
    @Override
    public void storeOrders(List<Order> newOrders) throws IOException {
        List<Order> existingOrders = retrieveOrders();
        existingOrders.addAll(newOrders);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Order order : existingOrders) {
                if (order != null) {
                    String csvLine = orderToCsvLine(order);
                    // Ensure not to write empty csv lines
                    if (!csvLine.isEmpty()) {
                        writer.write(csvLine);
                        writer.newLine();
                    }
                }
            }
        }
    }

    /**
     * Retrieves all orders from the CSV file, ensuring consistent data availability across
     * multiple executions of the application.
     *
     * @return a list of all orders retrieved from the CSV file.
     * @throws IOException if an error occurs during file reading.
     */
    @Override
    public List<Order> retrieveOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                orders.add(csvLineToOrder(line));
            }
        }
        return orders;
    }

    /**
     * Converts a given Order object into a CSV formatted string.
     *
     * @param order the order to convert.
     * @return a CSV formatted string representing the order's products.
     */
    private String orderToCsvLine(Order order) {
        StringBuilder sb = new StringBuilder();
        List<Product> productsToStore = order.getProducts().stream().filter(i -> i instanceof BeverageProduct).toList();
        for (Product product : productsToStore) {
            sb.append(product.getName()).append(",").append(product.getPrice()).append(";");
        }
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1); // Remove the last semicolon
        }
        return sb.toString();
    }

    /**
     * Parses a CSV formatted string to construct an Order object.
     * Ensures that the parser is robust against empty and malformed CSV lines.
     *
     * @param csvLine the string containing CSV formatted order data.
     * @return an Order constructed from the parsed data, or null if the csvLine is empty or malformed.
     */
    private Order csvLineToOrder(String csvLine) {
        if (csvLine == null || csvLine.isEmpty()) {
            return null; // or optionally, return new Order(new ArrayList<>()) to signify an empty order
        }

        String[] productDetails = csvLine.split(";");
        List<Product> products = new ArrayList<>();

        for (String detail : productDetails) {
            if (detail.isEmpty()) {
                continue; // skip empty product entries
            }

            String[] product = detail.split(",");
            if (product.length < 2) {
                continue; // skip this detail if it's not complete
            }

            String name = product[0].trim(); // trim to remove any unwanted whitespace
            double price = parsePrice(product[1].trim());
            if (name.isEmpty() || Double.isNaN(price)) {
                continue; // skip malformed entries
            }

            products.add(new ConcreteProduct(name, price));
        }

        return new Order(products);
    }

    /**
     * Safely parses a string to a double, handling possible number format exceptions.
     *
     * @param price the string representing the price.
     * @return the parsed double if valid, or Double.NaN if the string is not a valid double.
     */
    private double parsePrice(String price) {
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return Double.NaN; // signify an invalid price
        }
    }

    /**
     * A concrete implementation of the abstract Product class, allowing instantiation of product objects
     * necessary for storing order details.
     */
    private static class ConcreteProduct extends Product implements BeverageProduct {
        public ConcreteProduct(String name, double price) {
            super(name, price);
        }
    }
}