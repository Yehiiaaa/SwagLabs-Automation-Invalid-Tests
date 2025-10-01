package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class ProductPage {
    private final List<String> selectedItems = Arrays.asList(
            "Sauce Labs Backpack",
            "Sauce Labs Onesie",
            "Sauce Labs Fleece Jacket"
    );
    private final WebDriver driver;
    private final By pageTitle = By.className("product_label");
    private final By inventoryItems = By.className("inventory_item");
    private final By inventoryItemName = By.className("inventory_item_name");
    private final By inventoryItemButton = By.tagName("button");
    private final By cartIconButton = By.id("shopping_cart_container");

    private final By sauceLabsBackpack = By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']");
    private final By SauceLabsBackpackButton = By.xpath("//div[@class='inventory_item' and .//div[text()='Sauce Labs Backpack']]//button");
    private final By itemButton = By.className("btn_inventory");
    private final By backButton = By.className("inventory_details_back_button");

    private final By sideMenu = By.xpath("//button[text()='Open Menu']");
    private final By resetApp = By.id("reset_sidebar_link");
    private final By closeMenu = By.xpath("//button[text()='Close Menu']");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void assertOnPageTitle(String title) {
        Assert.assertEquals(driver.findElement(pageTitle).getText(), title);
    }

    public void selectProducts() {
        List<WebElement> allProducts = driver.findElements((inventoryItems));
        allProducts.stream()
                .filter(product -> selectedItems.contains(
                        product.findElement(inventoryItemName).getText()
                ))
                .forEach(product -> product.findElement(inventoryItemButton).click());
    }

    public void clickOnCartIcon() {
        driver.findElement(cartIconButton).click();
    }

    public void removeItemFromName() {
        driver.findElement(sauceLabsBackpack).click();
        driver.findElement(itemButton).click();
        driver.findElement(backButton).click();
        WebElement itemButton = driver.findElement(SauceLabsBackpackButton);
        String buttonText = itemButton.getText();
        Assert.assertEquals(buttonText, "REMOVE");
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void assertItemsSelected() {
        List<String> remainingSelectedItems = driver.findElements(inventoryItems).stream()
                .filter(product -> product.findElement(inventoryItemButton).getText().equals("REMOVE"))
                .map(product -> product.findElement(inventoryItemName).getText())
                .toList();

        Assert.assertEqualsNoOrder(remainingSelectedItems.toArray(), selectedItems.toArray());
    }

    public void resetAppState() throws InterruptedException {
        Thread.sleep(100);
        WebElement openMenuButton = driver.findElement(sideMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", openMenuButton);
        Thread.sleep(100);
        driver.findElement(resetApp).click();
        Thread.sleep(100);
        driver.findElement(closeMenu).click();
    }

    public void assertItemsSelectionStateAfterReset() {
        boolean hasRemoveButton = driver.findElements(inventoryItems).stream()
                .anyMatch(product -> product.findElement(inventoryItemButton).getText().equals("REMOVE"));
        Assert.assertFalse(hasRemoveButton);
    }

}
