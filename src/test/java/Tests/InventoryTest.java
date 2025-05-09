package Tests;

import Base.TestBase;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class InventoryTest extends TestBase {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Test
    public void validateInventoryPageNotEmpty() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(driver);
        inventoryPage.validateInventoryPage();
    }

    @Test
    public void validateAddToCartAndBadgeCount() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart(0);
        inventoryPage.validateCartBadgeCount(1);
    }

    @Test
    public void validateProductNameAndPrice() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(driver);
        inventoryPage.validateProductName(0, "Sauce Labs Backpack");
        inventoryPage.validateProductPrice(0, "$29.99");
    }

    @Test
    public void validateSortByNameAZ() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(driver);
        inventoryPage.sortProductsBy("Name (A to Z)");
        inventoryPage.validateProductSorting("Name (A to Z)");
    }

    @Test
    public void validateSortByPriceLowToHigh() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(driver);
        inventoryPage.sortProductsBy("Price (low to high)");
        inventoryPage.validateProductSorting("Price (low to high)");
    }
}