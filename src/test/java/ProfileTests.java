import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest{

    @Test
    public static void changeProfileNameTest () throws InterruptedException {


        //Open the URL for the web page on the chrome browser
        openLoginUrl();

        //Put the email field inside the web page
        enterEmail("supattra.tangsombutpaiboon@testpro.io");

        //Put the password field inside the web page
        enterPassword("te$t$tudent1");

        //Click on the submit button
        clickSubmit();
        Thread.sleep(2000);


        clickAvatarIcon();

        String randomName = generateRadomName();
        providePassword("te$t$tudent1");
        provideProfileName(randomName);
        clickSaveButton();
        Thread.sleep(2000);

        //Check if username has changed
        WebElement actualProfileName = driver.findElement(By.cssSelector("a.view-profile>span.name"));
        Assert.assertEquals(actualProfileName.getText(), randomName);
    }
}

