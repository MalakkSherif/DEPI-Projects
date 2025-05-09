package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By addToCartButtons = By.cssSelector(".btn_inventory");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By sortDropdown = By.className("product_sort_container");
    private final By itemNames = By.cssSelector(".inventory_item_name");
    private final By itemPrices = By.cssSelector(".inventory_item_price");

    // Actions
    public void navigateToInventoryPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    public void addToCart(int itemIndex) {
        driver.findElements(addToCartButtons).get(itemIndex).click();
    }

    public void sortProductsBy(String option) {
        driver.findElement(sortDropdown).sendKeys(option);
    }

    public void openCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public String getProductNameByIndex(int itemIndex) {
        return driver.findElements(itemNames).get(itemIndex).getText();
    }

    public String getProductPriceByIndex(int itemIndex) {
        return driver.findElements(itemPrices).get(itemIndex).getText();
    }

    public int getCartItemCount() {
        String badgeText = driver.findElement(cartBadge).getText();
        return Integer.parseInt(badgeText);
    }

    // Assertions
    public void validateInventoryPage() {
        Assert.assertFalse(driver.findElements(inventoryItems).isEmpty(), "Inventory page is empty.");
    }

    public void validateCartBadgeCount(int expectedCount) {
        String badgeText = driver.findElement(cartBadge).getText();
        int actualCount = Integer.parseInt(badgeText);
        Assert.assertEquals(actualCount, expectedCount, "Cart badge count is incorrect.");
    }

    public void validateProductName(int itemIndex, String expectedName) {
        String actualName = driver.findElements(itemNames).get(itemIndex).getText();
        Assert.assertEquals(actualName, expectedName, "Product name does not match.");
    }

    public void validateProductPrice(int itemIndex, String expectedPrice) {
        String actualPrice = driver.findElements(itemPrices).get(itemIndex).getText();
        Assert.assertEquals(actualPrice, expectedPrice, "Product price does not match.");
    }

    public void validateProductSorting(String sortOption) {
        sortProductsBy(sortOption);

        // Get product names
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < driver.findElements(itemNames).size(); i++) {
            productNames.add(driver.findElements(itemNames).get(i).getText());
        }

        // Sort product names based on the selected option
        List<String> sortedNames = new ArrayList<>(productNames);
        if (sortOption.equals("Name (A to Z)")) {
            Collections.sort(sortedNames);
        } else if (sortOption.equals("Price (low to high)")) {
            // Extract prices and sort by price
            List<Double> prices = new ArrayList<>();
            for (int i = 0; i < driver.findElements(itemPrices).size(); i++) {
                prices.add(Double.parseDouble(driver.findElements(itemPrices).get(i).getText().replace("$", "")));
            }

            // Sort prices and compare with the product list
            List<String> sortedPrices = new ArrayList<>();
            for (Double price : prices) {
                sortedPrices.add("$" + price);
            }
            Collections.sort(sortedPrices);

            // Verify that products are sorted by price
            Assert.assertEquals(sortedPrices, driver.findElements(itemPrices).get(0).getText(), "Products are not sorted by price.");
        }

        // Verify that the sorted list matches the displayed list
        Assert.assertEquals(productNames, sortedNames, "Products are not sorted correctly.");
    }
}