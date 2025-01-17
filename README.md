



# Project README

## Overview
This project is a **Robusta Task** automation framework built with **Java 21**, **Maven**, **TestNG**, and **Selenium**. It follows the **Page Object Model (POM)** design pattern for structuring tests and is specifically tailored for automating tasks related to Robusta.

## Prerequisites

Before running the tests, ensure you have the following tools installed:

- **Java 21** or higher
- **Maven**
- **IDE** (IntelliJ IDEA, Eclipse, etc.)
- **Browser**: Microsoft Edge
- **Driver Manager** for managing the Edge browser driver automatically

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://gitlab.com/task7401749/Robusta_Task
   cd Robusta_Task
   ```

2. **Install dependencies**:
   Run the following command to install all project dependencies using Maven:
   ```bash
   mvn clean install
   ```

3. **Configure Browser Driver**:
   - This project uses **Driver Manager** to handle the browser driver automatically. 
   - No manual download or configuration of the Edge driver is needed.
   - Ensure that the **WebDriverManager** is correctly set up to manage **EdgeDriver**.

4. **Test Data**:
   Ensure that the test data files are available in `src/test/resources/TestData/dynamicLogin/` or update the path in the code accordingly.

## Running Tests

1. **Run a single test class**:
   To run a specific test class, use the following Maven command:
   ```bash
   mvn test -Dtest=<TestClassName>
   ```

2. **Run all tests**:
   To run all tests, simply use:
   ```bash
   mvn test
   ```

3. **Run tests using TestNG XML**:
   To run tests using the TestNG XML configuration file (`UserJourney.xml`), use the following command:
   ```bash
   mvn test -DsuiteXmlFile=UserJourney.xml
   ```

4. **View the Allure report**:
   After running tests, generate the Allure report by running:
   ```bash
   allure serve test-outputs/target/allure-results
   ```
   The Allure report will be available in the `test-outputs/target/allure-results` folder.

## Test Output

- Test results will be displayed in the terminal or IDE.
- Allure reports will be available at `test-outputs/target/allure-results`.



