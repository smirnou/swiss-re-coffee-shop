## LLD design

> Please note: this is a DRAFT version of real LLD document.

## Purpose of LLD design

The purpose of the Low-Level Design (LLD) for the coffee shop software task is to provide a comprehensive and detailed blueprint that addresses the specific technical requirements and implementations necessary to build the software solution effectively. This LLD in ideal, outlines the architecture, data flow, classes, interfaces, and methods with precise attributes and operations, ensuring alignment with the system's high-level objectives and user requirements.

## Domain model classes
Domain model classes are described below:

![uml](/images/class-model-uml-diagram.png)

| Class                     | Attributes                                                                                           | Methods                                                                                                                                                                                                                                                                                                      | Description                                                           |
|:--------------------------|:-----------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------|
| [Product][prod_link]      | - name: String<br>- price: double                                                                    | + Product(name: String, price: double)<br>+ getName(): String<br>+ getPrice(): double<br>+ equals(Object): boolean<br>+ hashCode(): int                                                                                                                                                                      | Abstract class for coffee shop products.                              |
| [BaconRoll][bacon_link]   | Inherits from Product                                                                                | + BaconRoll(size: BaconRollSize)                                                                                                                                                                                                                                                                             | Represents a Bacon Roll product.                                      |
| [Coffee][coffee_link]     | Inherits from Product                                                                                | + Coffee(size: CoffeeSize)                                                                                                                                                                                                                                                                                   | Represents a Coffee product.                                          |
| [OrangeJuice][juice_link] | Inherits from Product                                                                                | + OrangeJuice(size: OrangeJuiceSize)                                                                                                                                                                                                                                                                         | Represents an Orange Juice product.                                   |
| [Order][order_link]       | - products: List<Product><br>- status: OrderStatus<br>- totalCost: double<br>- totalDiscount: double | + Order(products: List<Product>)<br>+ addProduct(product: Product)<br>+ getProducts(): List<Product><br>+ getStatus(): OrderStatus<br>+ setStatus(status: OrderStatus)<br>+ getTotalCost(): double<br>+ setTotalCost(totalCost: double)<br>+ applyDiscount(discount: double)<br>+ getTotalDiscount(): double | Manages orders, handling product lists and finances.                  |
| [ExtraItem][extra_link]   | Inherits from Product                                                                                | + ExtraItem(option: ExtraOption)                                                                                                                                                                                                                                                                             | Represents additional items (extras) that can enhance other products. |

 [prod_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/Product.java
 [bacon_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/BaconRoll.java
 [coffee_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/Coffee.java
 [juice_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/OrangeJuice.java
 [order_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/Order.java
 [extra_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/model/ExtraItem.java

## Services

Main important services and their workflow are described below:

![uml](/images/services-overview-diagram.png)

| # | Abstract class / interface | Service                                               | Description                                                                                                                                                                                                                                                                        |
|:--|:---------------------------|:------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | ProductInputHandler        | [CLIProductInputHandler][CLIProductInputHandler_link] | The [CLIProductInputHandler][CLIProductInputHandler_link] implements this interface for command-line based interaction, managing user inputs and directing them appropriately in the console environment.                                                                          |
| 2 | IOrderController           | [OrderController][OrderController_link]               | The [OrderController][OrderController_link] class orchestrates the processing of orders, calculation of payments, and the presentation of receipts. It serves as a mediator between the service layer (order and payment services) and the presentation layer (receipt presenter). |
| 3 | IOrderService              | [OrderService][OrderService_link]                     | The [OrderService][OrderService_link] class responsible for handling orders and their related operations, including applying any promotional strategies prior to finalizing the order total.                                                                                       |
| 4 | IBonusService              | [BonusService][BonusService_link]                     | The [BonusService][BonusService_link] class manages the application of various promotional (bonus) strategies to orders. This service allows for registration of multiple promotion (bonus) strategies and applies all registered strategies to a given order.                     |
| 5 | IPaymentService            | [PaymentService][PaymentService_link]                 | The [PaymentService][PaymentService_link] class responsible for handling financial transactions related to orders.                                                                                                                                                                 |
| 6 | ReceiptPresenter           | [CLIReceiptPresenter][CLIReceiptPresenter_link]       | Defines the structure for presenting purchase receipts in various formats. The [CLIReceiptPresenter][CLIReceiptPresenter_link] class is an implementation tailored for command-line environments, where it formats and displays the receipt details directly on the console.       |

 [CLIProductInputHandler_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/input/impl/CLIProductInputHandler.java
 [OrderController_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/controller/impl/OrderController.java
 [OrderService_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/service/impl/OrderService.java
 [BonusService_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/service/impl/BonusService.java
 [PaymentService_link]: https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/service/impl/PaymentService.java
 [CLIReceiptPresenter_link]:https://github.com/smirnou/swiss-re-coffee-shop/blob/main/src/main/java/org/epam/swissre/coffeeshop/receipt/impl/CLIReceiptPresenter.java

