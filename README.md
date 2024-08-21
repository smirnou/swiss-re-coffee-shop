#  Swiss Re: Coffee Shop PoC

This project is a Command Line Interface (CLI) based application designed for managing orders in a coffee shop.
It allows users to add products to an order, review the order, confirm it, and process payments.

## Features

- **Add Product Items**:  Choose coffee, orange juice, bacon rolls, and extras. Specify details like size and any additional options for each product selected.
- **Review & Confirm Order**: Check your order details and confirm.
- **Proceed to Payment**: Calculate discounts and complete the transaction and generate a receipt.

## Installation

### Prerequisites
- Java JDK 17
- Maven 3.6+

### Build the Application
Clone the repository and build the project with Maven:

```bash
git clone https://github.com/smirnou/swiss-re-coffee-shop.git
cd swiss-re-coffee-shop
mvn package
```
### Run the Application
To run the **Coffee Shop** application, please execute:

```bash
java -jar target/swiss-re-coffee-shop-1.0-SNAPSHOT.jar
```

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for more details.
