package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    private final WebDriver driver;

    private final By subTotal = By.className("summary_subtotal_label");
    private final By Tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finishButton = By.cssSelector("a[href=\"./checkout-complete.html\"]");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public Float getSubtotal() {
        return Float.parseFloat(Utility.getText(driver, subTotal).replace("Item total: $", ""));
    }

    public Float getTax() {
        return Float.parseFloat(Utility.getText(driver, Tax).replace("Tax: $", ""));
    }

    public Float getTotal() {
        return Float.parseFloat(Utility.getText(driver, total).replace("Total: $", ""));
    }

    public String calculateTotalPrice() {
        return String.valueOf(getSubtotal() + getTax());
    }

    public Boolean comparingPrices() {
        return calculateTotalPrice().equals(String.valueOf(getTotal()));
    }

    public FinishingOrder clickOnFinishButton() {
        Utility.clickingOnElement(driver, finishButton);
        return new FinishingOrder(driver);
    }


}
