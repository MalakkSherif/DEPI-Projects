package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By cartItems = By.className("cart_item");
    private final By removeButtons = By.cssSelector(".cart_button");
    private final By checkoutButton = By.id("checkout");

    public void openCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeItem(int index) {
        driver.findElements(removeButtons).get(index).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    // Assertions
    public void validateItemInCart() {
        Assert.assertTrue(getCartItemCount() > 0, "No item found in the cart.");
    }

    public void validateItemRemoved() {
        Assert.assertEquals(getCartItemCount(), 0, "Item not removed from cart.");
    }

    public void validateEmptyCartCheckoutBehavior() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart.html"), "User should not proceed to checkout with empty cart.");
    }
}

