package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OnesiePage {
    private final By addToCartButton = By.xpath("//*[text()='ADD TO CART']");
    private final By removeButton = By.xpath("//*[text()='REMOVE']");
    private final WebDriver driver;
    private final By numberOfProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By productName = By.xpath("//*[text()='Sauce Labs Onesie']");
    private final By productPrice = By.xpath("//*[text()='7.99']");
   ;

    public OnesiePage(WebDriver driver) {
        this.driver=driver;
    }

    public OnesiePage clickOnAddToCart (){
        Utility.clickingOnElement(driver,addToCartButton);
        return new OnesiePage(driver);
    }
    public Boolean verifyProductIsAddedToCart(){
        return driver.findElement(removeButton).isDisplayed();
    }
    public String getNumberOfProductsOnCartIcon() {

        try {

            LogsUtils.info("number of products on cart " + Utility.getText(driver, numberOfProductsOnCartIcon));
            return Utility.getText(driver, numberOfProductsOnCartIcon);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }

        return "0";
    }
    public boolean comparingNumberOfSelectedProductsWithCart() {
        return getNumberOfProductsOnCartIcon().equals("2");
    }
    public CartPage clickOnCartIcon() {
        Utility.clickingOnElement(driver, cartIcon);
        return new CartPage(driver);
    }
    public Boolean verifyCartPageURl(String expectedURl) {
        try {
            Utility.generalWait(driver).until(ExpectedConditions.urlToBe(expectedURl));
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }

        return true;
    }
    public String getProductName() {
        return driver.findElement(productName).getText();
    }
    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }







}
