package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.CartPage;
import Pages.OnesiePage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;


@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class CartTest {
    private final String username = DataUtils.getData("dynamicLogin", "validData.username");
    private final String password = DataUtils.getData("dynamicLogin", "validData.password");



    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Browser was opened");
        getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info(" browser is redirected to the url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @Test
    public void clickOnCheckoutButton() {
        new LoginPage(getDriver()).
                enterUsername(username).enterPassword(password).clickOnLoginButton();
        LogsUtils.info("login Successfully");
        new HomePage(getDriver()).addFleeceJacketToCart();
        Assert.assertTrue(new HomePage(getDriver()).fleeceJacketAddToCartButtonIsDisplayed());
        LogsUtils.info("the fleece jacked was added to the cart successfully");
        new HomePage(getDriver()).openOnesiePage();
        Assert.assertTrue(new HomePage(getDriver()).verifyOnesiePageIsOpened(getDriver().getCurrentUrl()));
        LogsUtils.info("the Onesie page was opened successfully");
        new OnesiePage(getDriver()).clickOnAddToCart();
        Assert.assertTrue(new OnesiePage(getDriver()).verifyProductIsAddedToCart());
        LogsUtils.info("the onesie product was added to the cart successfully");
        Assert.assertTrue(new OnesiePage(getDriver()).comparingNumberOfSelectedProductsWithCart());
        new OnesiePage(getDriver()).clickOnCartIcon();
        Assert.assertTrue(new OnesiePage(getDriver()).verifyCartPageURl(getDriver().getCurrentUrl()));
        LogsUtils.info("the cart was opened successfully");
        Assert.assertTrue(new CartPage(getDriver()).verifyFirstProductNameIsDisplayed());
        Assert.assertTrue(new CartPage(getDriver()).verifySecondProductPriceIsDisplayed());
        Assert.assertTrue(new CartPage(getDriver()).verifyFirstProductPriceIsDisplayed());
        Assert.assertTrue(new CartPage(getDriver()).verifySecondProductNameIsDisplayed());
        new CartPage(getDriver()).clickOnCheckoutButton();
        Assert.assertTrue(new CartPage(getDriver()).verifyOpeningCheckoutPage(getDriver().getCurrentUrl()));
        LogsUtils.info("the checkout page was opened successfully");
    }



    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }


}
