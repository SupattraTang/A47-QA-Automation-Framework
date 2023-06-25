import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
//    @Test
    public static void LoginValidEmailPasswordTest() {
        //Open the URL for the web page on the chrome browser
        openLoginUrl();

        //Put the email field inside the web page
        enterEmail("supattra.tangsombutpaiboon@testpro.io");

        //Put the password field inside the web page
        enterPassword("te$t$tudent1");

        //Click on the submit button
        clickSubmit();

        //Check if the avatar user is display (success login check)
        WebElement avatar = driver.findElement(By.cssSelector(".avatar"));
        Assert.assertTrue(avatar.isDisplayed());

    }


//    @Test
    public static void LoginEmptyEmailPasswordTest() {
        openLoginUrl();
        //Put the email field inside the web page
        enterEmail("");
        //Put the password field inside the web page
        enterPassword("");
        clickSubmit();
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

}
