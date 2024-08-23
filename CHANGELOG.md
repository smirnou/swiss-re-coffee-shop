# Application Version History

This document maintains a log of changes for each release of the CoffeeShop application.

## [1.3-SNAPSHOT] - 2024-08-23

### Changed
- Created a new snapshot version. No changes or new features introduced in this snapshot, solely an increment to the version number to align with development and versioning protocols.

## [1.2-SNAPSHOT] - 2024-08-22

> Artifact is available [here](https://github.com/smirnou/swiss-re-coffee-shop/actions/runs/10524296112)

### Added
- Integration of the JaCoCo Maven plugin to generate coverage reports.
- Configuration for excluding specific classes and packages in JaCoCo coverage reports to focus on meaningful code areas.

### Improved
- Code coverage metrics significantly enhanced by refining tests and optimizing code.

## [1.1-SNAPSHOT] - 2024-08-22

> Artifact is available [here](https://github.com/smirnou/swiss-re-coffee-shop/actions/runs/10505310675)

### Added
- Implemented data persistence using a CSV file:
    - The CoffeeShop application now uses a CSV file to store and track orders from previous sessions.
    - This persistence ensures that the customer’s cumulative purchase history is considered when applying bonuses, such as the "every 5th beverage is free" offer.

### Changed
- The bonus application system now checks the new persistent CSV file to accurately track and apply the 'Customer Stamp Card' bonus across sessions.

### Fixed
- Ensured that past orders are counted towards the 'Customer Stamp Card' promotion to accurately administer the free beverage offer on every fifth purchase, even across application restarts.

### Known Issues
- **Single User Limitation**: Currently, the persistence mechanism supports only one user's data (his orders).

## [1.0-SNAPSHOT] - 2024-08-21

> Artifact is available [here](https://github.com/smirnou/swiss-re-coffee-shop/actions/runs/10493864487)

### Added
- Initial release of the CoffeeShop application.
- Implemented core business logic for handling coffee orders.
- Ordering system allows for selection of product type (large coffee with extra milk, small coffee with special roast, bacon roll, orange juice).
- Introduced bonus functions:
    - Customer Stamp Card (by default): Every 5th beverage is free.
    - Combined Purchase Offer: When a beverage and a snack are ordered together, one of the extras on the beverage is free.
- Basic user interface (CLI) supporting order creation and summary.
- Temporary in-memory storage for sessions without data persistence across restarts.
