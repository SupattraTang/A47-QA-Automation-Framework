import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void LoginEmptyEmailPasswordTest(String email, String password) throws InterruptedException{

        //Navigate to "https://qa.koel.app/".
//        openLoginUrl();
        //Log in with your credentials.
        //enterEmail("supattra.tangsombutpaiboon@testpro.io");
        //enterPassword("te$t$tudent1");
        enterEmail(email);
        enterPassword(password);
        clickSubmit();
    }
}