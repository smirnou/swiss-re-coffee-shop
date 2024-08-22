package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.CoffeeSize;
import org.epam.swissre.coffeeshop.enums.ExtraOption;
import org.epam.swissre.coffeeshop.enums.OrderStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Order}.
 */
public class OrderTest {
    private Order order;

    @BeforeEach
    public void setUp() {
        // Set up with dummy products, assuming there are concrete classes, or you could mock them
        Product product1 = new Coffee(CoffeeSize.LARGE);
        Product product2 = new ExtraItem(ExtraOption.EXTRA_MILK);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        order = new Order(products);
    }

    /**
     * Test that order initializes with the correct status and that products are added correctly.
     */
    @Test
    public void testOrderInitializationAndProductAddition() {
        assertNotNull(order, "Order should be created");
        assertEquals(2, order.getProducts().size(), "Order should contain two products");
        assertEquals(OrderStatus.OPEN, order.getStatus(), "Order status should initially be OPEN");
    }

    /**
     * Test setting and updating the order status.
     */
    @Test
    public void testSettingOrderStatus() {
        order.setStatus(OrderStatus.PAID);
        assertEquals(OrderStatus.PAID, order.getStatus(), "Order status should be updated to PAID");
    }

    /**
     * Test setting the order total cost and applying a discount.
     */
    @Test
    public void testSettingTotalCostAndApplyingDiscount() {
        order.setTotalCost(20.0);
        assertEquals(20.0, order.getTotalCost(), "Total cost should be set to 20.0");

        order.applyDiscount(5.0);
        assertEquals(15.0, order.getTotalCost(), "Total cost should be reduced to 15.0 after applying 5.0 discount");
        assertEquals(5.0, order.getTotalDiscount(), "Total discount should be recorded as 5.0");
    }

    /**
     * Test discount error handling when a negative value is applied.
     */
    @Test
    public void testApplyingNegativeDiscount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> order.applyDiscount(-5.0));
        assertTrue(exception.getMessage().contains("Discount cannot be negative"), "Negative discount should throw IllegalArgumentException");
    }

    /**
     * Test error handling for setting null status.
     */
    @Test
    public void testSettingNullStatus() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> order.setStatus(null));
        assertTrue(exception.getMessage().contains("Order status cannot be null"), "Setting null status should throw IllegalArgumentException");
    }

    /**
     * Test adding a null product.
     */
    @Test
    public void testAddingNullProduct() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> order.addProduct(null));
        assertTrue(exception.getMessage().contains("Product cannot be null"), "Adding null product should throw IllegalArgumentException");
    }
}