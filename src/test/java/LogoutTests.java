import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTests extends BaseTest {

    @Test
    public void Loginout() {

        openLoginUrl();
        enterEmail("supattra.tangsombutpaiboon@testpro.io");
        enterPassword("te$t$tudent1");
        clickSubmit();

        //Check if the avatar user is display (success login check)
        WebElement avatar = driver.findElement(By.cssSelector(".avatar"));
        Assert.assertTrue(avatar.isDisplayed());

        clickLogout();

        //Navigate back to koel login page
        WebElement koelPic = driver.findElement(By.cssSelector("div.logo"));
        Assert.assertTrue(koelPic.isDisplayed());
        driver.quit();

    }

}
