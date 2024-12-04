package Pages;


import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CartPage {
    private final WebDriver driver;
    private final By checkOutButton = By.cssSelector("[href='./checkout-step-one.html']");

    private final By firstProductName = By.xpath("//*[text()='Sauce Labs Fleece Jacket']");
    private final By secondProductName = By.xpath("//*[text()='Sauce Labs Onesie']");
    private final By firstProductPrice = By.xpath("//div[@class='inventory_item_price' and contains(., '49.99')]");
    private final By secondProductPrice = By.xpath("//div[@class='inventory_item_price' and contains(., '7.99')]");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean verifyFirstProductNameIsDisplayed() {
        return driver.findElement(firstProductName).isDisplayed();
    }

    public Boolean verifyFirstProductPriceIsDisplayed() {
        return driver.findElement(firstProductPrice).isDisplayed();
    }

    public Boolean verifySecondProductNameIsDisplayed() {
        return driver.findElement(secondProductName).isDisplayed();
    }

    public Boolean verifySecondProductPriceIsDisplayed() {
        return driver.findElement(secondProductPrice).isDisplayed();
    }


    public CheckoutPage clickOnCheckoutButton() {
        Utility.clickingOnElement(driver, checkOutButton);
        return new CheckoutPage(driver);
    }
    public Boolean verifyOpeningCheckoutPage(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }
    public String getFirstProductName() {
        return driver.findElement(firstProductName).getText();
    }
    public String getFirstProductPrice() {
        return driver.findElement(firstProductPrice).getText();
    }
    public String getSecondProductName() {
        return driver.findElement(secondProductName).getText();
    }
    public String getSecondProductPrice() {
        return driver.findElement(secondProductPrice).getText();
    }

}
