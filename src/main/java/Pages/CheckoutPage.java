package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage {
    private final WebDriver driver;
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueButton = By.xpath("//input[@type='submit']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;


    }



    public void enterUserData() {
        // Fetch user data (replace 1 with any user ID)
        String[] userData = Utility.getUserData(1);

        if (userData != null) {
            String firstName = userData[0];
            String lastName = userData[1];
            String postalCode = userData[2];

            // Use this data in your page, e.g., fill forms, validate data, etc.
            LogsUtils.info("First Name: " + firstName);
            LogsUtils.info("Last Name: " + lastName);
            LogsUtils.info("Zip/Postal Code: " + postalCode);

            // Example: Enter this data into form fields (assuming locators are set)
            driver.findElement(firstNameField).sendKeys(firstName);
            driver.findElement(lastNameField).sendKeys(lastName);
            driver.findElement(zipCode).sendKeys(postalCode);
        }
    }

    public Boolean verifyFirstname() {
        return driver.findElement(By.id("first-name")).getAttribute("value").equals("Leanne");
    }
    public Boolean verifyLastname() {
        return driver.findElement(lastNameField).getAttribute("value").equals("Graham");
    }
    public Boolean verifyZipcode() {
        return driver.findElement(zipCode).getAttribute("value").equals("92998-3874");
    }
    public Boolean verifyOpeningCheckoutPageTwo(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }





    public OverviewPage clickOnContinueButton() {
        Utility.clickingOnElement(driver, continueButton);
        return new OverviewPage(driver);
    }

    public Boolean verifyURl(String expectedURl) {
        try {
            Utility.generalWait(driver).until(ExpectedConditions.urlToBe(expectedURl));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
