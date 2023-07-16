package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.UUID;

public class ProfilePage extends BasePage{
    public ProfilePage (WebDriver givenDriver) {
        super(givenDriver);
    }

    private By currentPasswordField = By.cssSelector("#inputProfileCurrentPassword");
    private By usernameField = By.cssSelector("#inputProfileName");
    private By emailField = By.cssSelector("#inputProfileEmail");
    private By newPasswordField = By.cssSelector("#inputProfileNewPassword");
    private By saveBtn = By.cssSelector("button.btn-submit");
    private By actualProfileName = By.cssSelector("a.view-profile>span.name");

    public String generateUsername(){
        String name = UUID.randomUUID().toString().replace("-", "");
        return name.substring(0, 7);
    }

    public void enterCurrentPassword(String password){
        findElement(currentPasswordField).sendKeys(password);
    }
    public void enterNewUsername(String username){
        findElement(usernameField).sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE, username);
    }
    public void enterNewEmail(String email){
        findElement(emailField).sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE, email);
    }
    public void enterNewPassword(String password){
        findElement(newPasswordField).sendKeys(password);
    }
    public void clickSaveBtn() {
        findElement(saveBtn).click();
    }
}
