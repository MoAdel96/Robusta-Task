package Tests;

import Pages.*;
import Utilities.DataUtils;
import Utilities.LogsUtils;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class UserJourneyTest {


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

    @Test(testName = "User Journey", groups = {"Order"})
    @Description("logging in, adding products to the cart, verifying the contents, and completing a purchase using data from an external API.")
    @Owner("Mohamed Adel")
    @Severity(CRITICAL)
    @AllureId("1")
    @Epic("Order")
    @Feature("Order cycle")
    @Story("User Journey")
    public void userJourney() {
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
        Assert.assertEquals(new HomePage(getDriver()).getProductName(),new CartPage(getDriver()).getFirstProductName());
        //Assert.assertEquals(new HomePage(getDriver()).getProductPrice(),new CartPage(getDriver()).getFirstProductPrice());
        Assert.assertEquals(new OnesiePage(getDriver()).getProductName(),new CartPage(getDriver()).getSecondProductName());
        Assert.assertEquals(new OnesiePage(getDriver()).getProductPrice(),new CartPage(getDriver()).getSecondProductPrice());

        new CartPage(getDriver()).clickOnCheckoutButton();
        Assert.assertTrue(new CartPage(getDriver()).verifyOpeningCheckoutPage(getDriver().getCurrentUrl()));
        LogsUtils.info("the checkout page was opened successfully");
        new CheckoutPage(getDriver()).enterUserData();
        Assert.assertTrue(new CheckoutPage(getDriver()).verifyFirstname());
        LogsUtils.info("first name is wright");
        Assert.assertTrue(new CheckoutPage(getDriver()).verifyLastname());
        LogsUtils.info("last name is wright");
        Assert.assertTrue(new CheckoutPage(getDriver()).verifyZipcode());
        LogsUtils.info("zipcode is wright");
        new CheckoutPage(getDriver()).clickOnContinueButton();
        Assert.assertTrue(new CheckoutPage(getDriver()).verifyOpeningCheckoutPageTwo(getDriver().getCurrentUrl()));
        LogsUtils.info("user is redirected to the overview page");
        Assert.assertTrue(new OverviewPage(getDriver()).comparingPrices());
        LogsUtils.info("the total price is correct");
        new OverviewPage(getDriver()).clickOnFinishButton();
        Assert.assertTrue(new FinishingOrder(getDriver()).checkVisibilityOfThanksMessage());
    }


    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }
}
