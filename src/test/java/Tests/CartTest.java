package Tests;

import Base.TestBase;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CartTest extends TestBase {

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @Test
    public void TC_009_validateItemInCart() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart(0);

        cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.validateItemInCart();
    }

    @Test
    public void TC_010_validateItemRemoved() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart(1);

        cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.removeItem(0);
        cartPage.validateItemRemoved();
    }

    @Test
    public void TC_011_cartRetentionOnRefresh() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart(2);

        driver.navigate().refresh();

        cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.validateItemInCart();
    }

    @Test
    public void TC_012_cartPersistsAfterLogoutLogin() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart(3);
        inventoryPage.addToCart(4);

        // Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        // Login again
        loginPage.login("standard_user", "secret_sauce");

        cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.validateItemInCart();
    }

    @Test
    public void TC_013_checkoutWithEmptyCartShouldFail() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();
        cartPage.validateEmptyCartCheckoutBehavior();
    }
}
