import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest{

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public static void changeProfileNameTest (String email, String password) {
        //Put the email field inside the web page
        enterEmail(email);
        //Put the password field inside the web page
        enterPassword(password);
        //Click on the submit button
        clickSubmit();

        clickAvatarIcon();

        String randomName = generateRandomName();
        providePassword("te$t$tudent1");
        provideProfileName(randomName);
        clickSaveButton();

        //Check if username has changed
        WebElement actualProfileName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.view-profile>span.name")));
        Assert.assertEquals(actualProfileName.getText(), randomName);
    }
}