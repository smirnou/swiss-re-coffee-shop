# LLD design

# Table of Contents
1. [Introduction](#introduction)
2. [Technical design](#technical-design)
3. [Purpose of LLD design](#purpose-of-lld-design)
4. [Class diagram](#class-diagram)
 * [Domain model classes](#domain-model-classes)
 * [Services & workflow](#services--workflow)
5. [Sequence diagram](#sequence-diagram)
6. [Persistence](#persistence)
 * [OrderStorage Overview](#orderstorage-overview)
 * [Key Features](#key-features)
 * [Rationale for Implementing File-based Persistence](#rationale-for-implementing-file-based-persistence)
 * [Example CSV Content](#example-csv-content)
 * [Conclusion](#conclusion)

# Introduction

This project is a Command Line Interface (CLI) based application designed for managing orders in a CoffeeShop application.
It allows users to add products to an order, review the order, confirm it, and process payments.

# Technical design

## Purpose of LLD design

The purpose of the Low-Level Design (LLD) for the coffee shop software task is to provide a comprehensive and detailed blueprint that addresses the specific technical requirements and implementations necessary to build the software solution effectively. This LLD in ideal, outlines the architecture, data flow, classes, interfaces, and methods with precise attributes and operations, ensuring alignment with the system's high-level objectives and user requirements.

## Class diagram

### Domain model classes
Domain model classes are described below:

![uml](/images/class-model-uml-diagram.png)

| # | Class                            | Attributes                                                                                                                                    | Methods                                                                                                                                                                                                                                                                                                                                                                                                        | Description                                                                                                                                             |
|:--|:---------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | [Product][prod_link]             | - name: String<br>- price: double                                                                                                             | + Product(name: String, price: double)<br>+ getName(): String<br>+ getPrice(): double<br>+ equals(Object): boolean<br>+ hashCode(): int                                                                                                                                                                                                                                                                        | Abstract class for coffee shop products.                                                                                                                |
| 2 | [BaconRoll][bacon_link]          | Inherits from Product                                                                                                                         | + BaconRoll(size: BaconRollSize)                                                                                                                                                                                                                                                                                                                                                                               | Represents a Bacon Roll product.                                                                                                                        |
| 3 | [Coffee][coffee_link]            | Inherits from Product                                                                                                                         | + Coffee(size: CoffeeSize)                                                                                                                                                                                                                                                                                                                                                                                     | Represents a Coffee product.                                                                                                                            |
| 4 | [OrangeJuice][juice_link]        | Inherits from Product                                                                                                                         | + OrangeJuice(size: OrangeJuiceSize)                                                                                                                                                                                                                                                                                                                                                                           | Represents an Orange Juice product.                                                                                                                     |
| 5 | [Order][order_link]              | - products: List<Product><br>- alreadyPaidProducts : List<Product><br>- status: OrderStatus<br>- totalCost: double<br>- totalDiscount: double | + Order(products: List<Product>)<br>+ addProduct(product: Product)<br>+ getProducts(): List<Product><br>+ getAlreadyPaidProducts(): List<Product><br>+ setAlreadyPaidProducts(products: List<Product>)<br>+ getStatus(): OrderStatus<br>+ setStatus(status: OrderStatus)<br>+ getTotalCost(): double<br>+ setTotalCost(totalCost: double)<br>+ applyDiscount(discount: double)<br>+ getTotalDiscount(): double | Manages orders, handling product lists and finances, including tracking of already paid products.                                                       |
| 6 | [ExtraItem][extra_link]          | Inherits from Product                                                                                                                         | + ExtraItem(option: ExtraOption)                                                                                                                                                                                                                                                                                                                                                                               | Represents additional items (extras) that can enhance other products.                                                                                   |
| 7 | [BeverageProduct][beverage_link] | None                                                                                                                                          | None                                                                                                                                                                                                                                                                                                                                                                                                           | Marker interface for beverage-type products, used for type distinction and enforcing a contract segregating beverage products from other product types. |

 [prod_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/Product.java
 [bacon_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/BaconRoll.java
 [coffee_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/Coffee.java
 [juice_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/OrangeJuice.java
 [order_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/Order.java
 [extra_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/ExtraItem.java
[beverage_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/BeverageProduct.java

### Services & workflow

Main important services and their workflow are described below:

![uml](/images/services-overview-diagram.png)

| # | Abstract class / interface | Service                                               | Description                                                                                                                                                                                                                                                                        |
|:--|:---------------------------|:------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | ProductInputHandler        | [CLIProductInputHandler][CLIProductInputHandler_link] | The [CLIProductInputHandler][CLIProductInputHandler_link] implements this interface for command-line based interaction, managing user inputs and directing them appropriately in the console environment.                                                                          |
| 3 | IOrderController           | [OrderController][OrderController_link]               | The [OrderController][OrderController_link] class orchestrates the processing of orders, calculation of payments, and the presentation of receipts. It serves as a mediator between the service layer (order and payment services) and the presentation layer (receipt presenter). |
| 2 | IOrderStorage              | [OrderStorage][OrderStorage_link]                     | The [OrderStorage][OrderStorage_link] class manages the storage and retrieval of Orders, specifically the products within an order, to and from a CSV file.                                                                                                                        |
| 4 | IOrderService              | [OrderService][OrderService_link]                     | The [OrderService][OrderService_link] class responsible for handling orders and their related operations, including applying any promotional strategies prior to finalizing the order total.                                                                                       |
| 5 | IBonusService              | [BonusService][BonusService_link]                     | The [BonusService][BonusService_link] class manages the application of various promotional (bonus) strategies to orders. This service allows for registration of multiple promotion (bonus) strategies and applies all registered strategies to a given order.                     |
| 6 | IPaymentService            | [PaymentService][PaymentService_link]                 | The [PaymentService][PaymentService_link] class responsible for handling financial transactions related to orders.                                                                                                                                                                 |
| 7 | ReceiptPresenter           | [CLIReceiptPresenter][CLIReceiptPresenter_link]       | Defines the structure for presenting purchase receipts in various formats. The [CLIReceiptPresenter][CLIReceiptPresenter_link] class is an implementation tailored for command-line environments, where it formats and displays the receipt details directly on the console.       |

 [CLIProductInputHandler_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/input/impl/CLIProductInputHandler.java
 [OrderController_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/controller/impl/OrderController.java
 [OrderStorage_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/service/impl/OrderStorage.java
 [OrderService_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/service/impl/OrderService.java
 [BonusService_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/service/impl/BonusService.java
 [PaymentService_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/service/impl/PaymentService.java
 [CLIReceiptPresenter_link]:https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/receipt/impl/CLIReceiptPresenter.java

## Sequence diagram
Sequence diagram is described below:

![uml](/images/sequence-uml-diagram.png)


## Persistence

### OrderStorage Overview

The `OrderStorage` service, part of the `org.epam.swissre.coffeeshop.service.impl` package, handles the persistence of coffee shop orders using a CSV file. This module is integral to maintaining state across multiple executions by storing and retrieving order data.

### Key Features

- **File Initialization**: Ensures a CSV file at the specified path exists or creates a new one.
- **Data Management**: Appends new orders and retrieves existing ones from the CSV file.

### Rationale for Implementing File-based Persistence

> NOTE: Part of requirements: There is no Graphical User Interface required and no persistence.

Persisting data using a CSV file offers several practical benefits for the CoffeeShop application:

1. **Continuity**: Maintains order history across sessions, crucial for customer engagement and managing promotions like "every 5th beverage free".
2. **Ease of Integration**: Given the small scale of the application, CSV files provide an easy-to-manage solution without the complexity and overhead of full-blown database systems.
3. **Compliance and Testing**: Using Java SE’s native I/O capabilities ensures compliance with project constraints and facilitates efficient test-driven development (TDD), aligning perfectly with project requirements.

This approach not only simplifies the technical implementation but also ensures robust functionality for loyalty programs and business analytics without additional dependencies.

### Example CSV Content

```csv
Small coffee,2.55;Medium coffee,3.05
Fresh Orange Juice (0.25l),3.95;Fresh Orange Juice (0.25l),3.95
```

Each line records a separate order, with products denoted by name and price, separated by semicolons.

### Conclusion

Utilizing a CSV file for order storage is a straightforward yet effective solution for ensuring data availability and promotional accuracy in coffee shop applications, balancing simplicity with functionality.
