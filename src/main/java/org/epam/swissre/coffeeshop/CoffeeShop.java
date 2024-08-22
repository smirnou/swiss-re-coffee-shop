package org.epam.swissre.coffeeshop;

import org.epam.swissre.coffeeshop.bonus.impl.EveryFifthBeverageFreeBonus;
import org.epam.swissre.coffeeshop.bonus.impl.FreeExtraWithBeverageAndSnackBonus;
import org.epam.swissre.coffeeshop.controller.*;
import org.epam.swissre.coffeeshop.controller.impl.*;
import org.epam.swissre.coffeeshop.input.*;
import org.epam.swissre.coffeeshop.input.impl.*;
import org.epam.swissre.coffeeshop.receipt.*;
import org.epam.swissre.coffeeshop.receipt.impl.*;
import org.epam.swissre.coffeeshop.service.*;
import org.epam.swissre.coffeeshop.service.impl.*;

/**
 * This class represents the main entry point for the Coffee Shop application.
 * It initializes the system and facilitates user interaction through a command-line interface.
 * Users can interact with the menu to select products such as coffee, bacon rolls, and orange juice.
 * It uses {@link CLIProductInputHandler} to handle user inputs and choices effectively.
 *
 * <p>The CoffeeShop class primarily governs the initiation and termination of the application while
 * delegating the user input and product handling tasks to the {@link CLIProductInputHandler}.</p>
 */
public class CoffeeShop {

    /**
     * Main method for the Coffee Shop application.
     * It sets up necessary components and starts the user interaction loop.
     *
     * @param args Command-line arguments passed during the start of the program, not used in this application.
     */
    public static void main(String[] args) {
        String filePath = "orders.csv"; // // This stores the file in the project's root or current working directory. Also, you can use home or tmp dir.

        // Initialize services and presenter (CLI for our case)
        IBonusService bonusService = new BonusService();
        bonusService.registerBonusStrategy(new EveryFifthBeverageFreeBonus());
        bonusService.registerBonusStrategy(new FreeExtraWithBeverageAndSnackBonus());

        IOrderController orderController = initOrderController(bonusService, filePath);

        // Initialize the Product Input handler (could implement a simple in-memory storage)
        ProductInput productInput = new SimpleProductInput(); // You will need to implement this according to ProductInput interface.
        ProductInputHandler inputHandler = new CLIProductInputHandler(productInput);

        System.out.println("Welcome to the Coffee Shop!");

        // Start handling the user input for product selection and operations.
        inputHandler.handleUserInput();

        // Process the order using OrderController
        if (productInput.isReadyToPay()) {
            orderController.processOrder(productInput.getProducts());
        } // else exit.

        // Exit Message
        System.out.println("\nThank you for visiting the Coffee Shop! Have a great day!");
    }

    private static IOrderController initOrderController(IBonusService bonusService, String filePath) {
        IOrderService orderService = new OrderService(bonusService);
        IOrderStorage orderStorage = new OrderStorage(filePath);
        IPaymentService paymentService = new PaymentService();

        ReceiptPresenter receiptPresenter = new CLIReceiptPresenter(); // Assuming a concrete implementation exists`

        // Create an instance of OrderController with the initialized services and presenter
        return new OrderController(orderService, orderStorage, paymentService, receiptPresenter);
    }
}