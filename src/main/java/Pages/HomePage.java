package Pages;


import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {



    private final WebDriver driver;
    private final By cartIcon = By.id("shopping_cart_container");
    private final By fleeceJacketAddToCartButton = By.xpath("(//button[contains(@class, 'btn_primary') and contains(text(), 'ADD TO CART')])[4]");
    private final By fleeceJacketRemoveButton = By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[3]/button");
    private final By firstProductName =By.xpath("//*[text()='Sauce Labs Fleece Jacket']");
    private final By firstProductPrice =By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[3]/div/text()[2]");


    private final By onesiePage = By.id("item_2_title_link");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public HomePage addFleeceJacketToCart() {

       Utility.clickingOnElement(driver, fleeceJacketAddToCartButton);
        return this;
    }

    public Boolean fleeceJacketAddToCartButtonIsDisplayed() {
        return driver.findElement(fleeceJacketRemoveButton).isDisplayed();
    }

    public OnesiePage openOnesiePage() {
        Utility.clickingOnElement(driver, onesiePage);
        return new OnesiePage(driver);
    }

    public Boolean verifyOnesiePageIsOpened(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }
    public CartPage clickOnCartIcon() {
        Utility.clickingOnElement(driver, cartIcon);
        return new CartPage(driver);
    }

    public String getProductName() {
        return driver.findElement(firstProductName).getText();
    }

    public String getProductPrice() {
        //Locator is correct but the driver can not find it

       return String.valueOf(Float.parseFloat(Utility.getText(driver,firstProductPrice).replace("$", "")));

    }





}

