package Tests;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.LoginPage;
import Pages.HomePage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class HomeTest {
    private final String username = DataUtils.getData("dynamicLogin", "validData.username");
    private final String password = DataUtils.getData("dynamicLogin", "validData.password");


    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Browser was opened");
        getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info(" browser is redirected to the url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new LoginPage(getDriver()).
                enterUsername(username).enterPassword(password).clickOnLoginButton();

    }

    @Test
    public void addFleeceJacketToCart() {
        new HomePage(getDriver()).addFleeceJacketToCart();
        Assert.assertTrue(new HomePage(getDriver()).fleeceJacketAddToCartButtonIsDisplayed());
    }
    @Test
    public void openOnesiePage(){
        new HomePage(getDriver()).openOnesiePage();
        Assert.assertTrue(new HomePage(getDriver()).verifyOnesiePageIsOpened(getDriver().getCurrentUrl()));
    }




    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }




}
