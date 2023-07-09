package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    private By emailField = By.cssSelector("[type='email']");
    private By passwordField = By.cssSelector("[type='password']");
    private By submitBtn = By.cssSelector("button[type='submit']");

    public void provideEmail(String email) {
        findElement(emailField).sendKeys(email);
    }

    public void providePassword(String password) {
        findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit() {
        findElement(submitBtn).click();
    }

    public void login() {
        provideEmail("supattra.tangsombutpaiboon@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmit();
    }
}