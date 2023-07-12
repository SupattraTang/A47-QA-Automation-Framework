package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.UUID;

public class ProfilePage extends BasePage{
    public ProfilePage (WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(css="#inputProfileCurrentPassword")
    WebElement currentPasswordField;
    @FindBy(css="#inputProfileName")
    WebElement usernameField;
    @FindBy(css = "#inputProfileEmail")
    WebElement emailField;
    @FindBy(css = "#inputProfileNewPassword")
    WebElement newPasswordField;
    @FindBy(css = "button.btn-submit")
    WebElement saveBtn;
    @FindBy(css="a.view-profile>span.name")
    WebElement actualProfileName;



    public String generateUsername(){
        String name = UUID.randomUUID().toString().replace("-", "");
        return name.substring(0, 7);
    }

    public ProfilePage enterCurrentPassword(String password){
        currentPasswordField.sendKeys(password);
        return this; }
    public ProfilePage enterNewUsername(String username){
        usernameField.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE, username);
        return this; }
    public ProfilePage enterNewEmail(String email){
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE, email);
        return this; }
    public ProfilePage enterNewPassword(String password){
        newPasswordField.sendKeys(password);
        return this; }
    public ProfilePage clickSaveBtn() {
        saveBtn.click();
        return this;
    }
}
