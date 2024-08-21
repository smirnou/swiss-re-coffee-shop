package org.epam.swissre.coffeeshop.enums;

public enum OrderStatus {
    OPEN,       // Order has been created but not yet finalized or paid.
    PAID,       // Order has been paid.
    CANCELLED,  // Order has been cancelled.
    COMPLETED   // Order has been completed and delivered.
}
