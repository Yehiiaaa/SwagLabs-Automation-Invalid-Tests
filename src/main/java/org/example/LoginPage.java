package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {
    private final WebDriver driver;
    private final By userName = By.id("user-name");
    private final By password = By.id("password");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCredentials(String username, String pass) {
        driver.findElement(userName).sendKeys(username);
        driver.findElement(password).sendKeys(pass, Keys.ENTER);
    }

    public void assertOnErrorMessage() {
        WebElement errorMsg = driver.findElement(errorMessage);
        String actualErrorText = errorMsg.getText();
        Assert.assertTrue(actualErrorText.contains("Epic sadface"));
    }

    public void assertOnFieldSize() {
        String actualUsername = driver.findElement(userName).getAttribute("value");
        String actualPassword = driver.findElement(password).getAttribute("value");
        actualUsername = String.valueOf(actualUsername);
        actualPassword = String.valueOf(actualPassword);
        Assert.assertTrue(actualUsername.length() < 101);
        Assert.assertTrue(actualPassword.length() < 101);
    }

}
