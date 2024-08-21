package org.epam.swissre.coffeeshop.input.impl;

import org.epam.swissre.coffeeshop.enums.BaconRollSize;
import org.epam.swissre.coffeeshop.enums.CoffeeSize;
import org.epam.swissre.coffeeshop.enums.ExtraOption;
import org.epam.swissre.coffeeshop.enums.OrangeJuiceSize;
import org.epam.swissre.coffeeshop.input.ProductInput;
import org.epam.swissre.coffeeshop.input.ProductInputHandler;
import org.epam.swissre.coffeeshop.model.*;
import org.epam.swissre.coffeeshop.util.FormatUtils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles Command Line Interface (CLI) interactions for user selections
 * in a coffee shop application. This class provides functionality to order
 * products like coffee and add them to a product list managed by a {@link ProductInput} instance.
 */
public class CLIProductInputHandler implements ProductInputHandler {
    private final Scanner scanner;
    private final ProductInput productInput;

    /**
     * Creates an instance of CLIProductInputHandler with the specified product input handler.
     *
     * @param productInput the {@link ProductInput} instance used for handling product data.
     */
    public CLIProductInputHandler(ProductInput productInput) {
        this.scanner = new Scanner(System.in);
        this.productInput = productInput;
    }

    /**
     * Processes user choices from the main menu to either order products or manage application actions.
     */
    @Override
    public void handleUserInput() {
        Product item;
        while (true) {
            int choice = displayMainMenu();
            switch (choice) {
                case 1:
                    orderCoffee();
                    break;
                case 2:
                    System.out.printf("You chose Orange Juice 0.25l (%.2f CHF)%n", OrangeJuiceSize.SMALL.getPriceInCHF());
                    item = new OrangeJuice(OrangeJuiceSize.SMALL);
                    productInput.addProduct(item);
                    break;
                case 3:
                    System.out.printf("You chose Bacon Roll (%.2f CHF)%n", BaconRollSize.STANDARD.getPriceInCHF());
                    item = new BaconRoll(BaconRollSize.STANDARD);
                    productInput.addProduct(item);
                    break;
                case 4:
                    orderExtra();
                    break;
                case 5:
                    reviewOrder();
                    break;
                case 6:
                    if (!productInput.isReadyToPay()) {
                        System.out.println("Please confirm your order. Select 'Review & Confirm Order' from the main menu.");
                        break;
                    } else return;
                case 7:
                    productInput.setReadyToPay(false);
                    System.out.println("Exit - Closing application.");
                    return;  // Exits the loop and effectively the method
                default:
                    System.out.println("Invalid option, please select a valid item.");
                    break;
            }
        }
    }

    /**
     * Displays main menu options to the user and captures their selection.
     *
     * @return the selected main menu option as an integer.
     */
    private int displayMainMenu() {
        System.out.println("Please choose an option:");
        System.out.println("1 - Order Coffee");
        System.out.println("2 - Order Orange Juice");
        System.out.println("3 - Order Bacon Roll");
        System.out.println("4 - Add Extra Items");
        System.out.println("5 - Review & Confirm Order");
        System.out.println("6 - Go to Payment");
        System.out.println("7 - Exit (without payment)");
        System.out.print("Choose an option: ");

        return getIntInputFromCLI("Please enter a number between 1 and 7.");
    }

    /**
     * Manages the coffee ordering process and adds ordered coffee to the product list.
     */
    private void orderCoffee() {
        System.out.println("You chose Coffee. What size?");
        System.out.printf("1 - Small (%.2f CHF)%n", CoffeeSize.SMALL.getPriceInCHF());
        System.out.printf("2 - Medium (%.2f CHF)%n", CoffeeSize.MEDIUM.getPriceInCHF());
        System.out.printf("3 - Large (%.2f CHF)%n", CoffeeSize.LARGE.getPriceInCHF());
        System.out.print("Choose an option: ");

        int size = getIntInputFromCLI("Please enter a number between 1 and 3.");

        Product coffee;
        switch (size) {
            case 1:
                coffee = new Coffee(CoffeeSize.SMALL);
                break;
            case 2:
                coffee = new Coffee(CoffeeSize.MEDIUM);
                break;
            case 3:
                coffee = new Coffee(CoffeeSize.LARGE);
                break;
            default:
                System.out.println("Invalid option, please select a valid size.");
                return; // early return to avoid adding null to productInput
        }

        productInput.addProduct(coffee);
    }

    private void orderExtra() {
        boolean isValidInput = false; // Flag to check if input is valid

        // simple validation, it can be changed with requirements and moved to the ProductValidator class
        if (!hasCoffee(productInput.getProducts())) {
            System.out.println("First select coffee and then you can add Extra items.");
            return;
        }

        System.out.println("You chose Extra. What is your option?");
        System.out.printf("1 - Extra milk (%.2f CHF)%n", ExtraOption.EXTRA_MILK.getPriceInCHF());
        System.out.printf("2 - Foamed milk (%.2f CHF)%n", ExtraOption.FOAMED_MILK.getPriceInCHF());
        System.out.printf("3 - Special roast coffee (%.2f CHF)%n", ExtraOption.SPECIAL_ROAST_COFFEE.getPriceInCHF());
        System.out.print("Choose an option: ");

        int size = getIntInputFromCLI("Please enter a number between 1 and 3.");

        Product extra;
        switch (size) {
            case 1:
                extra = new ExtraItem(ExtraOption.EXTRA_MILK);
                break;
            case 2:
                extra = new ExtraItem(ExtraOption.FOAMED_MILK);
                break;
            case 3:
                extra = new ExtraItem(ExtraOption.SPECIAL_ROAST_COFFEE);
                break;
            default:
                System.out.println("Invalid option, please select a valid size.");
                return; // early return to avoid adding null to productInput
        }

        productInput.addProduct(extra);
    }

    private boolean hasCoffee(List<Product> products) {
        return products.stream().anyMatch(i -> i instanceof Coffee);
    }

    private void reviewOrder() {
        boolean isValidInput = false; // Flag to check if input is valid

        if (productInput.getProducts().isEmpty()) {
            System.out.println("Please select products in the main menu.");
            return;
        }

        // Fetch and display all products added during this session (for demonstration purposes)
        System.out.println("Here's a summary of your order:");
        int productCount = 0;
        for (Product product : productInput.getProducts()) {
            productCount ++;
            System.out.println(FormatUtils.formatWithDotLeaders(productCount + ". " + product.getName(), product.getPrice()));
        }
        double totalCost = productInput.getProducts().stream().mapToDouble(Product::getPrice).sum();

        System.out.printf("Total cost (without discount): %.2f CHF:", totalCost);
        System.out.println("\nPlease confirm your order (yes/no):");

        // Decision-making based on user input.
        while (!isValidInput) {
            String choice = scanner.next().trim().toLowerCase();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case "yes":
                    // If user confirms, set the readiness to proceed to payment.
                    productInput.setReadyToPay(true);
                    isValidInput = true; // Set the flag to true
                    break;
                case "no":
                    // If user declines, set readiness to false.
                    productInput.setReadyToPay(false);
                    isValidInput = true; // Set the flag to true
                    break;
                default:
                    System.out.println("Invalid option, please type 'yes' or 'no'.");
                    break;  // Exit the method after displaying the message.
            }
        }
    }

    private int getIntInputFromCLI(String errorDescription) {
        boolean isValidInput = false; // Flag to check if input is valid
        int size = 0;
        while (!isValidInput) {
            try {
                size = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline
                isValidInput = true; // Set the flag to true if no exception was thrown
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. " + errorDescription);
                scanner.nextLine(); // Clear the buffer to avoid infinite loop
            }
        }
        return size;
    }
}