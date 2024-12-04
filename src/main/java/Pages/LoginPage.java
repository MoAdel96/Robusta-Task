package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

    }

    public LoginPage enterUsername(String usernameText) {
        Utility.sendData(driver, username, usernameText);
        return this;
    }

    public LoginPage enterPassword(String passwordText) {
        Utility.sendData(driver, password, passwordText);
        return this;
    }

    public HomePage clickOnLoginButton() {
        Utility.clickingOnElement(driver, loginButton);
        return new HomePage(driver);
    }

    public Boolean assertLoginTC(String expectedValue) {

        return driver.getCurrentUrl().equals(expectedValue);
    }

}
