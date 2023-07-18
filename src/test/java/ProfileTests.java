import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfileTests extends BaseTest {

    @Test
    public void changeProfileNameTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io").providePassword("te$t$tudent1").clickSubmit();

        homePage.chooseAvatarIcon();

        String randomName = generateRandomName();
        profilePage.enterCurrentPassword("te$t$tudent1");
        provideProfileName(randomName);
        profilePage.clickSaveBtn();

        Thread.sleep(5000);
        //Check if username has changed
        WebElement actualProfileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.view-profile>span.name")));
        Assert.assertEquals(actualProfileName.getText(), randomName);

    }

}