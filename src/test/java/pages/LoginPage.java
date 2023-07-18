package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

public class LoginPage extends BasePage {
    public static WebDriverWait wait = null;
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //WebElements
    @FindBy(css = "[type='email']")
    WebElement emailField;
    @FindBy(css = "[type='password']")
    WebElement passwordField;
    @FindBy(css = "button[type='submit']")
    WebElement submitButtonLocator;

    public LoginPage provideEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmit() {
        submitButtonLocator.click();
        return this;
    }

    public void login() {
        provideEmail("supattra.tangsombutpaiboon@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmit();
    }

    protected static void clickLogout() {
        WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".logout.control")));
        logoutBtn.click();
    }

    //Profile Tests Helper Functions
    protected static void clickAvatarIcon() {
        WebElement avatarIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".profile>a>img.avatar")));
        avatarIcon.isEnabled();
        avatarIcon.isDisplayed();
        avatarIcon.click();
    }

    protected static String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}