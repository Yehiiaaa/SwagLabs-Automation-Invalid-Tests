package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final By cartItem = By.className("cart_item");
    private final By itemButton = By.tagName("button");
    private final By inventoryItemName = By.className("inventory_item_name");
    private final By inventoryItem = By.className("inventory_item");
    private final By InventoryItemButton = By.className("btn_inventory");
    private final By backButton = By.className("inventory_details_back_button");
    private final By cartButton = By.className("cart_button");

    private final List<String> selectedItems = Arrays.asList(
            "Sauce Labs Backpack",
            "Sauce Labs Onesie",
            "Sauce Labs Fleece Jacket"
    );

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void removeItemFromCart() {
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        WebElement removedItem = cartItems.get(1);
        removedItem.findElement(itemButton).click();

        List<String> cartItemNames = driver.findElements(cartItem).stream()
                .map(item -> item.findElement(inventoryItemName).getText())
                .toList();

        driver.navigate().back();

        List<String> selectedOnPage = driver.findElements(inventoryItem).stream()
                .filter(product -> product.findElement(itemButton).getText().equals("REMOVE"))
                .map(product -> product.findElement(inventoryItemName).getText())
                .toList();

        Assert.assertEquals(selectedOnPage, cartItemNames);
    }

    public void removeItemFromCartItemName() {
        String itemToRemove = selectedItems.get(1);

        List<WebElement> cartItems = driver.findElements(cartItem);
        cartItems.stream()
                .filter(item -> item.findElement(inventoryItemName).getText()
                        .equals(itemToRemove))
                .findFirst()
                .ifPresent(item -> {
                    item.findElement(inventoryItemName).click();
                    driver.findElement(InventoryItemButton).click();
                    driver.findElement(backButton).click();
                });

        boolean isStillSelected = driver.findElements(inventoryItem).stream()
                .anyMatch(product -> product.findElement(inventoryItemName).getText()
                        .equals(itemToRemove));

        Assert.assertTrue(isStillSelected);
    }

    public void checkCartItemsAfterRefresh() {
        List<WebElement> cartItems = driver.findElements(cartItem);
        cartItems.forEach(item -> item.findElement(itemButton).click());
        driver.navigate().refresh();
        boolean[] itemStillAdded = {false};

        driver.findElements(inventoryItem).stream()
                .filter(product -> selectedItems.contains(
                        product.findElement(inventoryItem).getText()
                ))
                .forEach(product -> itemStillAdded[0] = true);

        Assert.assertFalse(itemStillAdded[0]);
    }

    public void checkCartItemsAfterReset() {
        boolean hasRemoveButton = driver.findElements(cartButton).stream()
                .anyMatch(button -> button.getText().equals("REMOVE"));
        Assert.assertFalse(hasRemoveButton);
    }
}
