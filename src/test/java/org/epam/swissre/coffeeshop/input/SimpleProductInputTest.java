package org.epam.swissre.coffeeshop.input;

import org.epam.swissre.coffeeshop.input.impl.SimpleProductInput;
import org.epam.swissre.coffeeshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link SimpleProductInput}.
 */
public class SimpleProductInputTest {
    private SimpleProductInput productInput;

    static class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price);
        }
    }

    @BeforeEach
    void setUp() {
        productInput = new SimpleProductInput();
    }

    @Test
    void whenAddProduct_thenProductIsAdded() {
        Product product = new TestProduct("Coffee", 2.50);
        productInput.addProduct(product);
        assertEquals(1, productInput.getProducts().size(), "Product list should have one product added.");
    }

    @Test
    void whenAddProductIsNull_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> productInput.addProduct(null),
                "Adding null product should throw IllegalArgumentException.");
    }

    @Test
    void whenGetProducts_thenReturnsCopyOfList() {
        Product product = new TestProduct("Orange Juice", 1.50);
        productInput.addProduct(product);
        List<Product> retrievedProducts = productInput.getProducts();
        assertNotSame(productInput.getProducts(), retrievedProducts,
                "Should return a new list object, not the original list.");
        assertEquals(productInput.getProducts().size(), retrievedProducts.size(),
                "Returned list size should be equal to the number of products added.");
    }

    @Test
    void whenGetProductsEmpty_thenEmptyListReturned() {
        assertTrue(productInput.getProducts().isEmpty(), "Should return an empty list if no products have been added.");
    }

    @Test
    void whenSetReadyToPay_thenReflectsCorrectly() {
        assertFalse(productInput.isReadyToPay(), "Initially should not be ready to pay.");
        productInput.setReadyToPay(true);
        assertTrue(productInput.isReadyToPay(), "Should be ready to pay after setter call.");
    }
}
