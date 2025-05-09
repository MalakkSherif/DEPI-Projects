package Tests;

import Base.TestBase;
import Pages.LoginPage;
import Pages.LogoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends TestBase {

    LoginPage loginPage;
    LogoutPage logoutPage;

    @Test
    public void TC_026_validateRedirectToLoginAfterLogout() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        logoutPage = new LogoutPage(driver);
        logoutPage.logout();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://www.saucedemo.com/"), "User not redirected to login page after logout.");
    }

    @Test
    public void TC_027_validateBackButtonBlockedAfterLogout() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        logoutPage = new LogoutPage(driver);
        logoutPage.logout();

        driver.navigate().back();
        boolean errorPresent = driver.getPageSource().contains("Epic sadface: You can only access '/inventory.html' when you are logged in.");
        Assert.assertTrue(errorPresent, "User was able to access inventory after logout using back button.");
    }
}
