package org.epam.swissre.coffeeshop.input;

import org.epam.swissre.coffeeshop.model.Product;

/**
 * The {@code ProductInputHandler} interface defines the contract for handling product inputs in various formats.
 * Implementers of this interface are responsible for gathering product order inputs from different sources
 * such as CLI, CSV files, web forms, etc., and processing those inputs to add products accordingly.
 *
 * <p>This interface enables the application to support multiple input methods dynamically, increasing flexibility
 * and adaptability to different environments or requirements. By conforming to this interface, the application
 * can easily switch or extend its input mechanisms without altering the core logic that processes the product inputs.</p>
 *
 * <p>Implementations should ensure that the input method correctly interacts with the provided {@link ProductInput}
 * for storing retrieved or processed product data.</p>
 */
public interface ProductInputHandler {

    /**
     * Handles user interaction or input collection based on the implementation.
     * This method is the starting point for collecting and processing inputs into {@link Product} entries.
     */
    void handleUserInput();
}
