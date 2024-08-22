package org.epam.swissre.coffeeshop.model;

/**
 * Marker interface used to represent a type of {@link Product} that qualifies as a beverage.
 * This interface is used primarily for type distinction and to enforce a contract in
 * the application which segregates beverage products from other product types.
 * Implementing this interface allows for the use of type checks and ensures that
 * certain methods or logic paths are executed only for beverage products. It carries
 * no methods or fields and serves only as a means to identify a product as a beverage.
 */
public interface BeverageProduct {
    // No method implementations required, serves as a type discriminator.
}
