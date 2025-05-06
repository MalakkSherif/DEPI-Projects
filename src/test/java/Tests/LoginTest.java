package Tests;

import Base.TestBase;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    LoginPage loginPage;

    @Test
    public void validateLoginOfStandard_user()
    {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
    }
    @Test
    public void validateLoginOfLocked_out_user()
    {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("locked_out_user", "secret_sauce");
    }
    @Test
    public void validateLoginOfProblem_user()
    {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("problem_user", "secret_sauce");
    }
    @Test
    public void validateLoginOfError_user()
    {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("error_user", "secret_sauce");
    }
    @Test
    public void validateLoginOfVisual_user()
    {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("visual_user", "secret_sauce");
    }

}