package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By locked_out_userMessage = By.xpath("//button[@class=\"error-button\"]");
    private final By errorMessage = By.cssSelector("[data-test='error']");



    //Actions
    public void navigateToLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    public void enterUserName(String userName) {
        driver.findElement(username).sendKeys(userName);
    }
    public void enterPassword(String password) {
        driver.findElement(this.password).sendKeys(password);
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


    public void login(String username, String password)
    {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }

    //Assertions
    public void validateSuccessfulLogin() {
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }
    public void validateLocked_out_userLogin() {
        Assert.assertTrue(driver.findElement(locked_out_userMessage).getText().contains("Sorry, this user has been locked out."));

    }
    public void validateErrorMessage(String expectedMessage) {
        String actualMessage = driver.findElement(errorMessage).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected error message to contain: '" + expectedMessage + "', but got: '" + actualMessage + "'");
    }

    public void validateInvalidCredentialsLogin() {
        login("invalid_user", "invalid_password");
        validateErrorMessage("Username and password do not match");
    }

    public void validateEmptyUsername() {
        login("", "secret_sauce");
        validateErrorMessage("Username is required");
    }

    public void validateEmptyPassword() {
        login("standard_user", "");
        validateErrorMessage("Password is required");
    }

}
