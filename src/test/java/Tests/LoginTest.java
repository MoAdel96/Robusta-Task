package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;


@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class LoginTest {
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
    public void validLoginTC() throws IOException {
        new LoginPage(getDriver()).
                enterUsername(username).enterPassword(password).clickOnLoginButton();


        Assert.assertTrue(new LoginPage(getDriver()).assertLoginTC(DataUtils
                .getPropertyValue("environment", "HOME_URL")));

    }


    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }
}
