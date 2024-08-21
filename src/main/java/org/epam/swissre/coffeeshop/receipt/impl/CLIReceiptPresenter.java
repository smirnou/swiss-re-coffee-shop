package org.epam.swissre.coffeeshop.receipt.impl;

import org.epam.swissre.coffeeshop.receipt.ReceiptPresenter;
import org.epam.swissre.coffeeshop.receipt.ReceiptRow;
import org.epam.swissre.coffeeshop.util.DateTimeUtils;
import org.epam.swissre.coffeeshop.util.FormatUtils;

/**
 * Extends the {@link ReceiptPresenter} to implement a mechanism for presenting receipt information on a
 * Command Line Interface (CLI). This presenter is specifically designed for console-based applications
 * where receipt details need to be output directly to the standard output (stdout).
 *
 * <p>The implementation showcases each item on the receipt, totals the cost, and includes additional
 * information like discounts applied and instructions for feedback. This level of detail is intended
 * to enhance the user experience in a CLI environment, presenting all necessary transaction data in
 * a clear and readable format.</p>
 *
 * <p>Utilizes utility classes such as {@link DateTimeUtils} for date retrieval and {@link FormatUtils}
 * for text formatting, integrating other components of the system for consistent data representation.</p>
 */
public class CLIReceiptPresenter extends ReceiptPresenter {

    /**
     * Overridden method from {@link ReceiptPresenter} to display the formatted receipt on command line.
     * This method prints each item and its price, uses formatting for better alignment in output, and provides
     * overall transaction summaries including total cost and total discounts received.
     */
    @Override
    public void presentReceipt() {
        System.out.println("\n=== Coffee Shop Receipt ===");
        System.out.println("Date:" + DateTimeUtils.getCurrentDateTime());
        System.out.println("\nItems:");

        int  rowCount = 0;
        for (ReceiptRow row : getReceiptRows()) {
            rowCount ++;
            System.out.println(FormatUtils.formatWithDotLeaders(rowCount + ". "+ row.getDescription(), row.getPrice()));
        }

        System.out.printf("\nTotal cost: CHF %.2f", getTotalCost());
        System.out.printf("\nTotal discount: CHF %.2f\n", getTotalDiscount());

        System.out.println("\n=== Thank You for Visiting! ===");
        System.out.println("Keep your receipt! Every 5th beverage is on us!");
        System.out.println("For feedback, call us: 123-456-789");
        System.out.println("=== END of Receipt ===");
    }
}
