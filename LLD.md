## LLD design

> Please note: this is a DRAFT version of real LLD document.

## Purpose of LLD design

The purpose of the Low-Level Design (LLD) for the coffee shop software task is to provide a comprehensive and detailed blueprint that addresses the specific technical requirements and implementations necessary to build the software solution effectively. This LLD in ideal, outlines the architecture, data flow, classes, interfaces, and methods with precise attributes and operations, ensuring alignment with the system's high-level objectives and user requirements.

## Domain model classes
Domain model classes are described below:

![uml](/images/class-model-uml-diagram.png)

<details>
  <summary>Click to expand...</summary>

```text
@startuml
' Set skin parameters for a cohesive visualization
skinparam classAttributeIconSize 0
skinparam roundcorner 20
skinparam class {
BackgroundColor White
ArrowColor Black
BorderColor Black
}

' Define the BeverageProduct interface with detailed description
interface BeverageProduct {
}
note right of BeverageProduct
**Marker interface for beverage-type products**

This interface serves as a type marker, indicating that a product qualifies as a beverage.
It is primarily used for type distinction and to enforce a contract which segregates
beverage products from other product types in the system.

Implementing this interface allows using type checks to ensure certain operations or
methods are applicable exclusively to beverages. It carries no methods or fields
and only serves to identify a product as a beverage.
end note

' Define Abstract Class Product with description
abstract class Product {
- name : String
- price : double
+ {abstract} Product(name : String, price : double)
+ getName() : String
+ getPrice() : double
+ equals(o : Object) : boolean
+ hashCode() : int
}
note left of Product
**Abstract base class for all products**

This abstract class represents a general framework for a product in the coffee shop.
It defines essential properties such as product name and price, which are common across
all derived product types.

The intention of this class is to be extended by specific product classes to diversify the
product offerings, maintaining consistent attributes like name and price that are crucial
for business operations.
end note

' Define relations - inheritance and interface implementation
BaconRoll -up-|> Product
Coffee -up-|> Product
ExtraItem -up-|> Product
OrangeJuice -up-|> Product

Coffee ..|> BeverageProduct
OrangeJuice ..|> BeverageProduct

' Define Product Subclasses
class BaconRoll {
+ BaconRoll(size : BaconRollSize)
}

class Coffee {
+ Coffee(size : CoffeeSize)
}

class ExtraItem {
+ ExtraItem(option : ExtraOption)
}

class OrangeJuice {
+ OrangeJuice(size : OrangeJuiceSize)
}

' Define Order class with existing description
class Order {
- products : List<Product>
- alreadyPaidProducts : List<Product>
- status : OrderStatus
- totalCost : double
- totalDiscount : double

    + Order(products : List<Product>)
    + addProduct(product : Product)
    + getProducts() : List<Product>
    + setAlreadyPaidProducts(alreadyPaidProducts : List<Product>)
    + getAlreadyPaidProducts() : List<Product>
    + getStatus() : OrderStatus
    + setStatus(status : OrderStatus)
    + getTotalCost() : double
    + setTotalCost(totalCost : double)
    + applyDiscount(discount : double)
    + getTotalDiscount() : double
}
note right of Order
**Order class**
Represents an order in the coffee shop, which can contain multiple products.
This class manages the collection of products, and the various states and financial
attributes of an order, such as total cost and discounts applied.
end note

' Show Composition relation between Order and Product
Order "1" *-- "*" Product
@enduml
```

</details>

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

## Services

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

