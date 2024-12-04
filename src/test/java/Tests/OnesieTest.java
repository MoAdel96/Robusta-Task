package Tests;

import Pages.LoginPage;
import Pages.OnesiePage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import static DriverFactory.DriverFactory.*;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class OnesieTest {

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
    public void AddProductToCart(){
        new LoginPage(getDriver()).
                enterUsername(username).enterPassword(password).clickOnLoginButton().openOnesiePage();
        new OnesiePage(getDriver()).clickOnAddToCart();
        Assert.assertTrue(new OnesiePage(getDriver()).verifyProductIsAddedToCart());
        Assert.assertTrue(new OnesiePage(getDriver()).comparingNumberOfSelectedProductsWithCart());
    }
    @Test
    public void clickOnCartIcon (){
        new LoginPage(getDriver()).
                enterUsername(username).enterPassword(password).clickOnLoginButton().openOnesiePage();
        new OnesiePage(getDriver()).clickOnAddToCart();
        Assert.assertTrue(new OnesiePage(getDriver()).verifyCartPageURl(getDriver().getCurrentUrl()));
    }




    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }


}
