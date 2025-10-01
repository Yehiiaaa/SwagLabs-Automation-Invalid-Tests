package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckoutPage {
    private final WebDriver driver;
    private final By checkoutButton = By.className("checkout_button");
    private final By pageTitle = By.className("subheader");
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By cartButton = By.className("cart_button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    public void assertEmptyCartCheckout() {
        Assert.assertNotEquals(driver.findElement(pageTitle).getText(), "Checkout: Your Information");
    }

    public void yourInformationDataFill(String firstN, String lastN, String postCode) {
        driver.findElement(firstName).sendKeys(firstN);
        driver.findElement(lastName).sendKeys(lastN);
        driver.findElement(postalCode).sendKeys(postCode);
        driver.findElement(cartButton).click();
    }

    public void assertOnErrorMessage() {
        try {
            WebElement errorMsg = driver.findElement(errorMessage);
            String actualErrorText = errorMsg.getText();
            Assert.assertTrue(actualErrorText.contains("Error"));
        } catch (NoSuchElementException e) {
            Assert.fail();
        }
    }

}
