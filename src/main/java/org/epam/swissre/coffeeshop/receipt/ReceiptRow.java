package org.epam.swissre.coffeeshop.receipt;

/**
 * Represents a single row in a receipt that includes the description and price of an item (product).
 */
public class ReceiptRow {
    private String description;
    private double price;

    /**
     * Constructs a new ReceiptRow with a specified description and price.
     *
     * @param description the description of the item
     * @param price the price of the item
     */
    public ReceiptRow(String description, double price) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Receipt description cannot be null or empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Receipt product price cannot be negative.");
        }
        this.description = description;
        this.price = price;
    }

    /**
     * Gets the description of the item in this receipt row.
     *
     * @return a string representing the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for this receipt row.
     *
     * @param description the new description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the item in this receipt row.
     *
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price for this receipt row.
     *
     * @param price the new price of the item (product)
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
