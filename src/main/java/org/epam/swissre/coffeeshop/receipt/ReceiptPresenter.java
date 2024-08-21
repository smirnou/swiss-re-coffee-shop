package org.epam.swissre.coffeeshop.receipt;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for presenting receipts. This class provides the base functionality to
 * add and manage receipt rows.
 */
public abstract class ReceiptPresenter {
    private final List<ReceiptRow> receiptRows;
    private double totalCost = 0.0;
    private double totalDiscount = 0.0;

    /**
     * Constructor to initialize the ReceiptPresenter.
     */
    public ReceiptPresenter() {
        this.receiptRows = new ArrayList<>();
    }

    /**
     * Adds a receipt row to the list of receipt rows.
     *
     * @param row the ReceiptRow to be added
     */
    public void addReceiptRow(ReceiptRow row) {
        this.receiptRows.add(row);
    }

    /**
     * Presents the receipt. The concrete implementation should define how the receipt is displayed.
     */
    public abstract void presentReceipt();

    public List<ReceiptRow> getReceiptRows() {
        return receiptRows;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
