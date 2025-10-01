# SwagLabs Automation Tests

Automated testing framework for **Swag Labs** using **Java, Selenium, TestNG, and Allure Reports**.  
This project covers **negative** test scenarios for login, product, cart, and checkout functionalities.

---

## 🚀 Tech Stack
- **Java 22+**
- **Selenium WebDriver**
- **TestNG**
- **Allure Reports**
- **Maven**

---

## 📂 Project Structure
```
SwagLabs-Automation-Tests/
│── src/
│ ├── main/java/org/example/ # Page Object Models
│ │ ├── LoginPage.java
│ │ ├── ProductPage.java
│ │ ├── CartPage.java
│ │ └── CheckoutPage.java
│ │
│ ├── main/resources/ # Test data & configuration
│ │ ├── config.properties #Config file containing the login url
│ │ ├── invalid_login_data.csv
│ │ └── cashout_data.xlsx
│ │
│ └── test/java/ # Test classes
│ ├── BaseTest.java
│ ├── LoginTest.java
│ ├── ProductPageTest.java
│ ├── CartPageTest.java
│ ├── CheckoutTest.java
│ └── utils
│   ├──ConfigLoader.java
├   ├──ExcelFileManager.java
│   └──CSVFileManager.java
│
│── allure-results/ # Raw results after test execution
│── allure-report/ # Generated HTML test report
│── pom.xml # Maven dependencies & build settings
│── testng.xml # TestNG suite runner
│── README.md # Project documentation
```

---
## 🔑 Key Features

- **Page Object Model (POM)** for maintainable test code
- **Data-driven testing** using:
    - CSV (`invalid_login_data.csv`) for invalid login scenarios
    - Excel (`cashout_data.xlsx`) for checkout invalid scenarios
- **Automatic screenshots** on test failures
- **Allure Reports** integration

---

## How to Run Tests

### 1. Clone the repository
```
git clone https://github.com/<your-username>/SwagLabs-Automation-Invalid-Tests.git
cd SwagLabs-Automation-Invalid-Tests
```

### 2. Run tests with Maven
```
mvn clean test
```

### 3. Generate Allure Report
```
allure generate --clean  
allure serve -h localhost
```
👉 This will automatically open the report in your browser.

---

## ✅ Example Test Scenarios
- **Login**
    - Valid login
    - Invalid login (wrong username/password, SQL injection, empty fields, etc.)
- **Products**
    - Add/remove items from cart
    - Add/remove items from item's name
    - Add items and refresh page
    - Reset app state
- **Cart**
    - Verify items persist after refresh
    - Validate empty cart
    - Remove items from item's name
- **Checkout**
    - Empty cart cashout
    - Missing or invalid information
    
    

---

## 📸 Screenshots
Failed tests will automatically capture screenshots and store them in:
```
/screenshots/
```

