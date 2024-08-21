package org.epam.swissre.coffeeshop.receipt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link ReceiptPresenter}.
 */
public class ReceiptPresenterTest {

    private ReceiptPresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new TestableReceiptPresenter();
    }

    /**
     * Test addReceiptRow() method to ensure receipt rows are added correctly.
     */
    @Test
    public void testAddReceiptRow() {
        ReceiptRow row1 = new ReceiptRow("Item 1", 100.0);
        ReceiptRow row2 = new ReceiptRow("Item 2", 200.0);
        List<ReceiptRow> receiptRows = presenter.getReceiptRows();
        presenter.addReceiptRow(row1);
        presenter.addReceiptRow(row2);
        assertEquals(2, receiptRows.size(), "Two rows should be added to receiptRows.");
        assertTrue(receiptRows.contains(row1), "ReceiptRows should contain row1.");
        assertTrue(receiptRows.contains(row2), "ReceiptRows should contain row2.");
    }

    /**
     * Tests setting and getting total cost.
     */
    @Test
    public void testSetTotalCost() {
        presenter.setTotalCost(250.0);
        assertEquals(250.0, presenter.getTotalCost(), "Total cost should be set to 250.0.");
    }

    /**
     * Tests setting and getting total discount.
     */
    @Test
    public void testSetTotalDiscount() {
        presenter.setTotalDiscount(50.0);
        assertEquals(50.0, presenter.getTotalDiscount(), "Total discount should be set to 50.0.");
    }

    /**
     * Concrete subclass of ReceiptPresenter used only for testing the abstract class.
     */
    private static class TestableReceiptPresenter extends ReceiptPresenter {
        @Override
        public void presentReceipt() {
            // This method is stubbed for the purpose of testing.
            // No implementation is needed as we're only testing non-abstract methods here.
        }
    }
}